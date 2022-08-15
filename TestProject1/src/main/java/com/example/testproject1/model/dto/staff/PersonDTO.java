package com.example.testproject1.model.dto.staff;

import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;

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
    private String secondName;
    /**
     * Имя
     */
    private String firstName;
    /**
     * Должность id
     */
    private UUID jobTittleId;
    /**
     * Ссылка на фото
     */
    private String photo;
    /**
     * Дата рождения
     */
    private Date birthDay;
    /**
     * Номер телефона
     */
    private String phoneNumber;
    /**
     * Департамент id работника
     */
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

    public void setJobTittleId(UUID jobTittleId) {
        this.jobTittleId = jobTittleId;
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

    public void setDepartmentId(UUID departmentId) {
        this.departmentId = departmentId;
    }

    public static PersonDTO.PersonDtoBuilder newBuilder() {
        return new PersonDTO().new PersonDtoBuilder();
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class PersonDtoBuilder {

        public PersonDtoBuilder setId(UUID uuid) {
            PersonDTO.this.id = uuid;
            return this;
        }

        public PersonDtoBuilder setLastName(String lastName) {
            PersonDTO.this.lastName = lastName;
            return this;
        }

        public PersonDtoBuilder setSecondName(String secondName) {
            PersonDTO.this.secondName = secondName;
            return this;
        }

        public PersonDtoBuilder setFirstName(String firstName) {
            PersonDTO.this.firstName = firstName;
            return this;
        }

        public PersonDtoBuilder setJobTittle(JobTittle jobTittle) {
            PersonDTO.this.jobTittleId = jobTittle.getId();
            return this;
        }

        public PersonDtoBuilder setPhoto(String photo) {
            PersonDTO.this.photo = photo;
            return this;
        }

        public PersonDtoBuilder setBirthDay(Date date) {
            PersonDTO.this.birthDay = date;
            return this;
        }

        public PersonDtoBuilder setPhoneNumber(String phoneNumber) {
            PersonDTO.this.phoneNumber = phoneNumber;
            return this;
        }

        public PersonDtoBuilder setDepartment(Department department) {
            PersonDTO.this.departmentId = department.getId();
            return this;
        }

        /**
         * Метод build
         *
         * @return Возвращает объект класса {@link PersonDTO}
         */
        public PersonDTO build() {
            return PersonDTO.this;
        }
    }
}
