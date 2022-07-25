package com.example.testproject1.model.person;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

/**
 * Класс Person наследуется от {@link Staff}
 *
 * @author smigranov
 */
@XmlRootElement
@XmlType(name = "person",propOrder = {"secondName", "firstName", "lastName","jobTittle","birthDay","phoneNumber","photo","department"})
public class Person extends Staff implements Comparable<Person>{
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
     * Должность
     */
    private JobTittle jobTittle;
    /**
     * Ссылка на фото
     */
    private String photo;
    /**
     * Дата рождения
     */
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date birthDay;
    /**
     * Номер телефона
     */
    private String phoneNumber;
    /**
     * Подразделение работника
     */
    private Department department;

    public Person() {
    }

    public void setId(UUID id){
        super.setId(id);
    }
    @XmlAttribute(name = "id")
    public UUID getId(){
        return super.getId();
    }
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
    @XmlElement(name = "department")
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}
