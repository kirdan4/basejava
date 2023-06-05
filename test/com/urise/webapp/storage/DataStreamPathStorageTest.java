package com.urise.webapp.storage;

import com.urise.webapp.storage.serialize.DataStreamSerializer;

public class DataStreamPathStorageTest extends AbstractStorageTest {

    public DataStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()) {
        });
    }
}
