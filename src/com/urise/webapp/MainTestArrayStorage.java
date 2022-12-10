package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractArrayStorage;
import com.urise.webapp.storage.SortedArrayStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private final static AbstractArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume();
        r1.setUuid("uuid2");
        final Resume r2 = new Resume();
        r2.setUuid("uuid1");
        final Resume r3 = new Resume();
        r3.setUuid("uuid4");
        final Resume r4 = new Resume();
        r4.setUuid("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Get r2: " + ARRAY_STORAGE.get(r2.getUuid()));
        System.out.println("Get r3: " + ARRAY_STORAGE.get(r3.getUuid()));
        System.out.println("Get r4: " + ARRAY_STORAGE.get(r4.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());
        printAll();

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        ARRAY_STORAGE.update(r1);

        printAll();

        ARRAY_STORAGE.delete(r1.getUuid());
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        r1.setUuid("uuid6");
        ARRAY_STORAGE.save(r1);
        printAll();

        ARRAY_STORAGE.delete("dummy");

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}