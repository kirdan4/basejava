package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public interface Storage {

    void save(Resume r);

    Resume get(String uuid);

    void update(Resume r);

    void delete(String uuid);

    Object[] getAll();

    int size();

    void clear();
}
