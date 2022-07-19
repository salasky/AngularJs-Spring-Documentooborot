package com.example.testproject1.model;

/**
 * Класс Организации.Наследуется от {@link Staff}
 *
 * @author smigranov
 */
public class Organization extends Staff{
    /**
     * Полное название организации
     */
    private String OrganizationFullName;
    /**
     * Короткое название организации
     */
    private String OrganizationShortName;
    /**
     * Руководитель организации
     */
    private String OrganizationSupervisor;
    /**
     * Контактный телефон организации
     */
    private String OrganizationContactNumber;
}
