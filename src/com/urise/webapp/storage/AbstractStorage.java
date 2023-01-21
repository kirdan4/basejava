package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume r) {
        Object searchElement = getSearchElement(r.getUuid());
        if (isExist(searchElement)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            doSave(r, searchElement);
        }
    }

    public void delete(String uuid) {
        Object searchElement = getSearchElement(uuid);
        if (!isExist(searchElement)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(searchElement);
        }
    }

    public Resume get(String uuid) {
        Object searchElement = getSearchElement(uuid);
        if (!isExist(searchElement)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(searchElement);
    }

    public void update(Resume r) {
        Object searchElement = getSearchElement(r.getUuid());
        if (!isExist(searchElement)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            doUpdate(r, searchElement);
        }
    }

    protected abstract Object getSearchElement(String uuid);

    protected abstract boolean isExist(Object r);

    protected abstract void doSave(Resume r, Object searchElement);

    protected abstract void doDelete(Object searchElement);

    protected abstract Resume doGet(Object searchElement);

    protected abstract void doUpdate(Resume r, Object searchElement);
}
