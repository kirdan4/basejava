package com.urise.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }
    @Override
    @Test
    @Ignore
    public void saveOverflow() {
        super.saveOverflow();
    }
}