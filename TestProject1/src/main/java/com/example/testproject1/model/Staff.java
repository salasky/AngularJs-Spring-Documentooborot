package com.example.testproject1.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Базовый класс BaseDocument для ....
 *
 * @author smigranov
 */
public class Staff {
    /**
     * идентификатор орг.структуры
     */
    protected UUID id;

    public Staff() {
    }

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
