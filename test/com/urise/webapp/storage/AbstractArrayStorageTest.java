package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void save() {
        storage.save(new Resume(UUID_4));
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(storage.get(UUID_4), new Resume(UUID_4));
    }

    @Test(expected = StorageException.class)
    public void overflowStorage() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Array is full ahead of time.");
        }
        storage.save(new Resume());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void get() {
        Assert.assertEquals(storage.get(UUID_1), new Resume(UUID_1));
        Assert.assertEquals(storage.get(UUID_2), new Resume(UUID_2));
        Assert.assertEquals(storage.get(UUID_3), new Resume(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_1));
        Assert.assertEquals(storage.get(UUID_1), new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        Resume[] castStorage = storage.getAll();
        Assert.assertEquals(3, storage.size());
        Assert.assertArrayEquals(storage.getAll(), castStorage);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        Resume[] emptyStorage = new Resume[0];
        storage.clear();
        Assert.assertEquals(0, storage.size());
        Assert.assertArrayEquals(storage.getAll(), emptyStorage);
    }
}