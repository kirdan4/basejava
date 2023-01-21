package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private final static ListStorage ARRAY_STORAGE = new ListStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid2");
        final Resume r2 = new Resume("uuid1");
        final Resume r3 = new Resume("uuid4");
        final Resume r4 = new Resume("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
//        ARRAY_STORAGE.save(r2);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Get r2: " + ARRAY_STORAGE.get(r2.getUuid()));
        System.out.println("Get r3: " + ARRAY_STORAGE.get(r3.getUuid()));
        System.out.println("Get r4: " + ARRAY_STORAGE.get(r4.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());
        printAll();

//        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        ARRAY_STORAGE.update(r1);

        printAll();

        ARRAY_STORAGE.delete(r2.getUuid());
        ARRAY_STORAGE.delete(r3.getUuid());
        ARRAY_STORAGE.delete(r4.getUuid());
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        ARRAY_STORAGE.save(r1);
        printAll();

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Object r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
