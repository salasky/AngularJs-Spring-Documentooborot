package com.example.testproject1.model.dto.staffdto;

import java.util.List;
import java.util.UUID;

public class OrganizationDTO {
    /**
     * UUID департамента
     */
    private UUID id;
    /**
     * Полное название организации
     */
    private String fullName;
    /**
     * Короткое название организации
     */
    private String shortName;
    /**
     * Руководитель организации
     */
    private String supervisor;
    /**
     * Контактные телефоны организации
     */
    private List<String> contactNumbers;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public List<String> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(List<String> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
