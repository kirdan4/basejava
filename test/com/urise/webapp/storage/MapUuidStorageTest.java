package com.urise.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

public class MapUuidStorageTest extends AbstractStorageTest {

    public MapUuidStorageTest() {
        super(new MapUuidStorage());
    }

    @Override
    @Test
    @Ignore
    public void saveOverflow() {
        super.saveOverflow();
    }
}