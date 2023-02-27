package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SearchKey> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    private static final Comparator<Resume> RESUME_COMPARATOR =
            Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public void save(Resume r) {
        LOG.info("Save " + r);
        SearchKey searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SearchKey searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SearchKey searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public void update(Resume r) {
        LOG.info("Update " + r);
        SearchKey searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public List<Resume> getAllSorted() {
        LOG.info("Get All Sorted");
        List<Resume> copyList = getStorage();
        copyList.sort(RESUME_COMPARATOR);
        return copyList;
    }

    protected abstract SearchKey getSearchKey(String uuid);

    protected abstract boolean isExist(SearchKey r);

    protected abstract void doSave(Resume r, SearchKey searchKey);

    protected abstract void doDelete(SearchKey searchKey);

    protected abstract Resume doGet(SearchKey searchKey);

    protected abstract void doUpdate(Resume r, SearchKey searchKey);

    protected abstract List<Resume> getStorage();

    private SearchKey getNotExistingSearchKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private SearchKey getExistingSearchKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
