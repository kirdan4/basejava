package com.urise.webapp.storage;

import com.urise.webapp.storage.serialize.JsonStreamSerializer;

public class JsonStreamPathStorageTest extends AbstractStorageTest {

    public JsonStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
