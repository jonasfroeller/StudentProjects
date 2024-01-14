package at.htlleonding.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentId implements Serializable {
    private String clazz;
    private int catalogNumber;

    public StudentId() {
    }

    public StudentId(String clazz, int catalogNumber) {
        this.setClazz(clazz);
        this.setCatalogNumber(catalogNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentId studentId = (StudentId) o;
        return getCatalogNumber() == studentId.getCatalogNumber() && Objects.equals(getClazz(), studentId.getClazz());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClazz(), getCatalogNumber());
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(int catalogNumber) {
        this.catalogNumber = catalogNumber;
    }
}
