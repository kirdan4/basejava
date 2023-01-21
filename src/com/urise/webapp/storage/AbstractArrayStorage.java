package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    protected void doSave(Resume r, Object searchElement) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertElement(r, (Integer) searchElement);
        size++;
    }

    @Override
    protected void doDelete(Object searchElement) {
        size--;
        fillDeletedElement((Integer) searchElement);
        storage[size] = null;
        System.out.printf("Резюме c Uuid = %s удалено.\n", searchElement);
    }

    @Override
    protected Resume doGet(Object searchElement) {
        return storage[(Integer) searchElement];
    }

    @Override
    protected void doUpdate(Resume r, Object searchElement) {
        storage[(Integer) searchElement] = r;
        System.out.printf("Резюме %s обновлено.", r.getUuid());
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("База резюме очищена.");
    }

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

    protected abstract Integer getSearchElement(String uuid);

    @Override
    protected boolean isExist(Object searchElement) {
        return (Integer) searchElement >= 0;
    }
}
