package com.example.testproject1.model.staff;

import com.example.testproject1.service.jaxb.DateSQLTimeAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

/**
 * Класс Person наследуется от {@link Staff}
 *
 * @author smigranov
 */
@XmlRootElement
@XmlType(name = "person",propOrder = {"secondName", "firstName", "lastName", "jobTittle", "birthDay", "phoneNumber", "photo", "department"})
public class Person extends Staff implements Comparable<Person>{
    /**
     * Отчество
     */
    @Column(name ="last_name")
    private String lastName;
    /**
     * Фамилия
     */
    @Column(name ="second_name")
    private String secondName;
    /**
     * Имя
     */
    @Column(name ="first_name")
    private String firstName;
    /**
     * Должность
     */
    @Column(name ="job_tittle")
    private JobTittle jobTittle;
    /**
     * Ссылка на фото
     */
    @Column(name ="photo")
    private String photo;
    /**
     * Дата рождения
     */
    @Column(name ="birth_day")
    private Date birthDay;
    /**
     * Номер телефона
     */
    @Column(name ="phone_number")
    private String phoneNumber;
    /**
     * Департамент работника
     */
    @Column(name ="department")
    private Department department;

    @XmlElement(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "secondName")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @XmlElement(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "jobTittle")
    public JobTittle getJobTittle() {
        return jobTittle;
    }

    public void setJobTittle(JobTittle jobTittle) {
        this.jobTittle = jobTittle;
    }

    @XmlElement(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @XmlElement(name = "birthDay")
    @XmlJavaTypeAdapter(DateSQLTimeAdapter.class)
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @XmlElement(name = "phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @XmlElement(name = "department")
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        Object[] personArgs = {id,lastName, secondName,firstName,jobTittle,photo,birthDay,phoneNumber};
        MessageFormat form = new MessageFormat(
                "id {0}, lastName= {1}, secondName= {2}, firstName= {3}, jobTittle= {4}, photo= {5}, birthDay= {6}, phoneNumber={7}");
        return form.format(personArgs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Person o) {
        return Comparator.comparing(Person::getSecondName).thenComparing(Person::getFirstName).compare(this, o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(lastName, person.lastName) && Objects.equals(secondName, person.secondName) && Objects.equals(firstName, person.firstName) && Objects.equals(jobTittle, person.jobTittle) && Objects.equals(photo, person.photo) && Objects.equals(birthDay, person.birthDay) && Objects.equals(phoneNumber, person.phoneNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lastName, secondName, firstName, jobTittle, photo, birthDay, phoneNumber);
    }
}
