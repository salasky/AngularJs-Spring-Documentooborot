package com.example.testproject1.model.department;

import com.example.testproject1.model.Staff;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

/**
 * Класс подразделение. Наследуется от {@link Staff}
 *
 * @author smigranov
 */
@XmlRootElement
@XmlType(name = "department",propOrder = {"departmentFullName", "departmentShortName", "departmentSupervisor","departmentContactNumber"})
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

    public void setId(UUID id){
        super.setId(id);
    }
    @XmlAttribute(name = "id")
    public UUID getId(){
        return super.getId();
    }
    @XmlElement(name = "departmentFullName")
    public String getDepartmentFullName() {
        return DepartmentFullName;
    }

    public void setDepartmentFullName(String departmentFullName) {
        DepartmentFullName = departmentFullName;
    }
    @XmlElement(name = "departmentShortName")
    public String getDepartmentShortName() {
        return DepartmentShortName;
    }

    public void setDepartmentShortName(String departmentShortName) {
        DepartmentShortName = departmentShortName;
    }
    @XmlElement(name = "departmentSupervisor")
    public String getDepartmentSupervisor() {
        return DepartmentSupervisor;
    }

    public void setDepartmentSupervisor(String departmentSupervisor) {
        DepartmentSupervisor = departmentSupervisor;
    }
    @XmlElement(name = "departmentContactNumber")
    public String getDepartmentContactNumber() {
        return DepartmentContactNumber;
    }

    public void setDepartmentContactNumber(String departmentContactNumber) {
        DepartmentContactNumber = departmentContactNumber;
    }
}
