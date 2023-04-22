package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serialize.StreamSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;


public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final StreamSerializer streamSerializer;

    protected PathStorage(String dir, StreamSerializer streamSerializer) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        this.streamSerializer = streamSerializer;
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(dir + " is not a directory");
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not readable/writable");
        }
    }

    @Override
    public int size() {
        return (int) getPathStream().count();
    }

    @Override
    public void clear() {
        getPathStream().forEach(this::doDelete);
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            doUpdate(r, Files.createFile(path));
        } catch (IOException e) {
            throw new StorageException("Path create error", r.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return streamSerializer.doRead(Files.newInputStream(path));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            streamSerializer.doWrite(r, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected List<Resume> getStorage() {
        return getPathStream().map(this::doGet).collect(toCollection(ArrayList::new));
    }

    private Stream<Path> getPathStream() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }
}