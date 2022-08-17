package com.example.testproject1.model.dto.staffdto;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.UUID;

/**
 * DTO класс для сущности {@link com.example.testproject1.model.staff.Person}
 *
 * @author smigranov
 */
public class PersonDTO {
    /**
     * UUID департамента
     */
    private UUID id;
    /**
     * Отчество
     */
    private String lastName;
    /**
     * Фамилия
     */
    @NotNull(message = "secondName не может быть null")
    private String secondName;
    /**
     * Имя
     */
    @NotNull(message = "firstName не может быть null")
    private String firstName;
    /**
     * Должность id
     */
    @NotNull(message = "jobTittleId не может быть null")
    private UUID jobTittleId;
    /**
     * Ссылка на фото
     */
    private String photo;
    /**
     * Дата рождения
     */
    @NotNull(message = "birthDay не может быть null")
    private Date birthDay;
    /**
     * Номер телефона
     */
    private String phoneNumber;
    /**
     * Департамент id работника
     */
    @NotNull(message = "departmentId не может быть null")
    private UUID departmentId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UUID getJobTittleId() {
        return jobTittleId;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UUID getDepartmentId() {
        return departmentId;
    }

    public void setJobTittleId(UUID jobTittleId) {
        this.jobTittleId = jobTittleId;
    }

    public void setDepartmentId(UUID departmentId) {
        this.departmentId = departmentId;
    }
}
