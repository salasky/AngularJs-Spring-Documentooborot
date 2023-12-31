package com.example.testproject1.model.staff;


import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;


/**
 * Базовый класс BaseDocument для {@link Person},{@link Department}, {@link Organization}
 *
 * @author smigranov
 */
@XmlRootElement(name = "staff")
@XmlSeeAlso({Person.class, Department.class, Organization.class})
public class Staff {
    /**
     * идентификатор орг.структуры
     */
    @Column(name = "id")
    protected UUID id;

    @XmlAttribute
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff staff = (Staff) o;
        return Objects.equals(id, staff.id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MessageFormat.format("Staff id={0}", id);
    }
}
