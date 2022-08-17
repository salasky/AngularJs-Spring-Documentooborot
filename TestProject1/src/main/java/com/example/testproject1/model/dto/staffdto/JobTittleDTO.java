package com.example.testproject1.model.dto.staffdto;

import java.util.UUID;

public class JobTittleDTO {
    /**
     * Идентификатор должности
     */
    private UUID id;
    /**
     * Наименование должности
     */
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
