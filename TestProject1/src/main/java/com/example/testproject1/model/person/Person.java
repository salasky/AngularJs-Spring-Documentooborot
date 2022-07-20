package com.example.testproject1.model.person;

import com.example.testproject1.model.Staff;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.UUID;

/**
 * Класс Person наследуется от {@link Staff}
 *
 * @author smigranov
 */
@XmlRootElement
@XmlType(name = "person",propOrder = {"secondName", "firstName", "lastName","jobTittle","birthDay","phoneNumber","photo"})
public class Person extends Staff{
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
    private Date birthDay;
    /**
     * Номер телефона
     */
    private String phoneNumber;


    public Person() {
    }

    public Person(String lastName, String secondName, String firstName, JobTittle jobTittle, String photo, Date birthDay, String phoneNumber) {
        this.lastName = lastName;
        this.secondName = secondName;
        this.firstName = firstName;
        this.jobTittle = jobTittle;
        this.photo = photo;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                ", id=" + id + " "+
                "lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", jobTittle=" + jobTittle +
                ", photo='" + photo + '\'' +
                ", birthDay=" + birthDay +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
