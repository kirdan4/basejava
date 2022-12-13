package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size > STORAGE_LIMIT) {
            System.out.println("Резюме не сохранено. Хранилище переполнено.");
        } else if (index >= 0) {
            System.out.printf("Резюме не сохранено. Резюме с таким Uuid = %s уже есть в базе.\n", r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", uuid);
        } else {
            size--;
            replacingDeletedElement(index);
            storage[size] = null;
            System.out.printf("Резюме c Uuid = %s удалено.\n", uuid);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", uuid);
            return null;
        }
        return storage[index];
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.printf("Резюме c Uuid = %s не существует.\n", r.getUuid());
        } else {
            storage[index] = r;
            System.out.printf("Резюме %s обновлено.", r.getUuid());
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

    protected abstract void insertElement(Resume r, int index);

    protected abstract void replacingDeletedElement(int index);

    protected abstract int getIndex(String uuid);
}
