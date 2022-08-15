package com.example.testproject1.model.dto;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * DTO класс для сущности {@link com.example.testproject1.model.staff.Department}
 *
 * @author smigranov
 */
public class DepartmentDTO {
    /**
     * UUID департамента
     */
    private UUID id;
    /**
     * Полное название департамента
     */
    @NotNull
    private String fullName;
    /**
     * Короткое название департамента
     */
    private String shortName;
    /**
     * Руководитель департамента
     */
    @NotNull
    private String supervisor;
    /**
     * Контактный телефон департамента
     */
    private String contactNumber;
    /**
     * Организация подразделения
     */
    @NotNull
    private UUID organizationId;

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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrganizationiD() {
        return organizationId;
    }

    public void setOrganizationiD(UUID organizationiD) {
        this.organizationId = organizationiD;
    }
}
