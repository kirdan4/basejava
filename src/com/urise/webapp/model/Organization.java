package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long SERIAL_VERSION_UID = 1L;

    public Organization() {
    }

    private String name;
    private String website;
    private List<Period> periods;

    public Organization(String nameOrganization, String website, List<Period> periods) {
        this.name = nameOrganization;
        this.website = website;
        this.periods = periods;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriods() {
        return periods;
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

