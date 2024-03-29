package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    private static final Comparator<Resume> RESUME_COMPARATOR =
            Comparator.comparing(Resume::getUuid);
    @Override
    protected void insertElement(Resume r, int index) {
        int insertedElement = -index - 1;
        if (storage[insertedElement] != null) {
            System.arraycopy(storage, insertedElement, storage,
                    insertedElement + 1, size - insertedElement);
        }
        storage[insertedElement] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        if (size != index) {
            System.arraycopy(storage , index + 1, storage, index, size - index);
        }
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "None");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}
