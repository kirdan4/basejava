import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int numberResumes;

    void clear() {
        Arrays.fill(storage, 0, numberResumes, null);
        numberResumes = 0;
        System.out.println("База резюме очищена.");
    }

    void save(Resume r) {
        storage[numberResumes] = r;
        numberResumes++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < numberResumes; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < numberResumes; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                numberResumes--;
                System.arraycopy(storage, (i + 1), storage, i, numberResumes - i);
                storage[numberResumes] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, numberResumes);
    }

    int size() {
        return numberResumes;
    }
}