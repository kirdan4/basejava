package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume r) {
        if (size > STORAGE_LIMIT) {
            System.out.println("Резюме не сохранено. Хранилище переполнено.");
        } else if (getIndex(r.getUuid()) != -1) {
            System.out.printf("Резюме не сохранено. Резюме с таким Uuid = %s уже есть в базе.\n", r.getUuid());
        } else {
            storage[size] = r;
            size++;
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", uuid);
            return null;
        }
        return storage[index];
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", r.getUuid());
        } else {
            storage[index] = r;
            System.out.printf("Резюме %s обновлено.", r.getUuid());
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", uuid);
        } else {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
            System.out.printf("Резюме c Uuid = %s удалено.\n", uuid);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}