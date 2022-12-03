package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static final int LIMIT_STORAGE = 10000;
    private final Resume[] storage = new Resume[LIMIT_STORAGE];
    private int size;

    public void save(Resume r) {
        if (isFreeSpace()) {
            System.out.println("Резюме не сохранено. Хранилище переполнено.");
        } else {
            if (isFound(r.getUuid())) {
                System.out.printf("Резюме не сохранено. Резюме с таким Uuid = %s уже есть в базе.\n", r.getUuid());
            } else {
                storage[size] = r;
                size++;
            }
        }
    }

    public void update(Resume r, String newUuid) {
        if (!isFound(r.getUuid())) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", r.getUuid());
        } else {
            r.setUuid(newUuid);
            System.out.printf("Uuid обновлен. Новый Uuid = %s.\n", newUuid);
        }
    }

    public Resume get(String uuid) {
        if (!isFound(uuid)) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", uuid);
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (!isFound(uuid)) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", uuid);
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    size--;
                    System.arraycopy(storage, (i + 1), storage, i, size - i);
                    storage[size] = null;
                    System.out.printf("Резюме c Uuid = %s удалено.\n", uuid);
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("База резюме очищена.");
    }

    private boolean isFound(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFreeSpace() {
        return size > LIMIT_STORAGE;
    }
}