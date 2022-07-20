package com.example.testproject1.model;

import com.example.testproject1.model.department.Department;
import com.example.testproject1.model.organization.Organization;
import com.example.testproject1.model.person.Person;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Objects;
import java.util.UUID;

/**
 * Базовый класс BaseDocument для {@link Person},{@link Department}, {@link Organization}
 *
 * @author smigranov
 */
@XmlRootElement(name = "staff")
@XmlSeeAlso({Person.class, Department.class,Organization.class})
public class Staff {
    /**
     * идентификатор орг.структуры
     */
    protected UUID id;
    public Staff() {
    }

    @XmlTransient
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff staff = (Staff) o;
        return Objects.equals(id, staff.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
