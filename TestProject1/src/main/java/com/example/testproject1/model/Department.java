package com.example.testproject1.model;

/**
 * Класс подразделение. Наследуется от {@link Staff}
 *
 * @author smigranov
 */
public class Department extends Staff{
    /**
     * Полное название департамента
     */
    private String DepartmentFullName;
    /**
     * Короткое название департамента
     */
    private String DepartmentShortName;
    /**
     * Руководитель департамента
     */
    private String DepartmentSupervisor;
    /**
     * Контактный телефон департамента
     */
    private String DepartmentContactNumber;
}
