package com.urise.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

public class MapResumeStorageTest extends AbstractStorageTest {

    public MapResumeStorageTest() {
        super(new MapResumeStorage());
    }

    @Override
    @Test
    @Ignore
    public void saveOverflow() {
        super.saveOverflow();
    }
}