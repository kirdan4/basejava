package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    ArrayList<Resume> listStorage = new ArrayList<>();

    protected Integer getSearchElement(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume r, Object searchElement) {
        listStorage.add(r);
    }

    @Override
    protected void doDelete(Object searchElement) {
        listStorage.remove((int) searchElement);
    }

    @Override
    protected Resume doGet(Object searchElement) {
        return listStorage.get((Integer) searchElement);
    }

    @Override
    protected void doUpdate(Resume r, Object searchElement) {
        listStorage.set((Integer) searchElement, r);
    }

    @Override
    public Object[] getAll() {
        return listStorage.toArray();
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    protected boolean isExist(Object searchElement) {
        return (Integer) searchElement >= 0;
    }
}
