package com.example.testproject1.model;

import java.util.Date;

/**
 * Класс Person наследуется от {@link Staff}
 *
 * @author smigranov
 */
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
}
