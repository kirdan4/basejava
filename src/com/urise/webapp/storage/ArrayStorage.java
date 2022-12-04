package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

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

    public void update(Resume r, String newUuid) {
        if (getIndex(r.getUuid()) == -1) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", r.getUuid());
        } else {
            r.setUuid(newUuid);
            System.out.printf("Uuid обновлен. Новый Uuid = %s.\n", newUuid);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", uuid);
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", uuid);
        } else {
            size--;
            System.arraycopy(storage, (index + 1), storage, index, size - index);
            storage[size] = null;
            System.out.printf("Резюме c Uuid = %s удалено.\n", uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
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

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}