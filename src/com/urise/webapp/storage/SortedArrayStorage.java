package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertElement(Resume r, int index) {
        int insertedElement = (index * -1) - 1;
        if (storage[insertedElement] != null) {
            System.arraycopy(storage, insertedElement, storage, insertedElement + 1, size - index);
        }
        storage[insertedElement] = r;
    }

    @Override
    protected void replacingDeletedElement(int index) {
        if (size != index) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
