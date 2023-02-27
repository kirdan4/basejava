package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private final String name;
    private final String website;
    private final List<Period> periods;

    public Organization(String nameOrganization, String website, List<Period> periods) {
        this.name = nameOrganization;
        this.website = website;
        this.periods = periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return name.equals(that.name) && Objects.equals(website, that.website) && periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, website, periods);
    }

    @Override
    public String toString() {
        return "\nORGANIZATION:" +
                "\nname = " + name +
                ", \nperiods = " + periods;
    }
}

