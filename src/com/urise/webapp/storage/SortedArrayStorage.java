package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size > STORAGE_LIMIT) {
            System.out.println("Резюме не сохранено. Хранилище переполнено.");
        } else if (index > 0) {
            System.out.printf("Резюме не сохранено. Резюме с таким Uuid = %s уже есть в базе.\n", r.getUuid());
        } else {
            if (storage[(index * -1) - 1] != null) {
                System.arraycopy(storage, (index * -1) - 1, storage, ((index * -1) - 1) + 1, size - index);
            }
            storage[(index * -1) - 1] = r;
            size++;
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", uuid);
            return null;
        }
        return storage[index];
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", r.getUuid());
        } else {
            storage[index] = r;
            System.out.printf("Резюме %s обновлено.", r.getUuid());
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", uuid);
        } else {
            size--;
            System.arraycopy(storage, (index + 1), storage, index, size - index);
            storage[size] = null;
            System.out.printf("Резюме c Uuid = %s удалено.\n", uuid);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
