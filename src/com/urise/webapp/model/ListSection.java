package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private static final long SERIAL_VERSION_UID = 1L;
    private final List<String> elements;

    public ListSection(List<String> elements) {
        Objects.requireNonNull(elements, "elements must not be null");
        this.elements = elements;
    }

    public List<String> getElements() {
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return elements.equals(that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}

