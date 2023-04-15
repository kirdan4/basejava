package com.urise.webapp.storage;

import com.urise.webapp.storage.serialize.ObjectStreamSerializer;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {

    public ObjectStreamFileStorageTest() {
        super(new AbstractFileStorage(STORAGE_DIR, new ObjectStreamSerializer()) {
        });
    }
}
