package com.example.testproject1.queryholder.staffqueryholder;

/**
 * Класс для хранения SQL запросов staff сущностей
 *
 * @author smigranov
 */
public class StaffQueryHolder {

    /**
     * Запрос на создание записи в таблице department
     */
    public static final String DEPARTMENT_CREATE_QUERY = "INSERT INTO department VALUES (?,?,?,?,?,?)";
    /**
     * Запрос на получение всех объектов из таблицы department
     */
    public static final String DEPARTMENT_GET_ALL_QUERY = new StringBuilder()
            .append("SELECT department.id AS department_id, department.full_name AS department_full_name,")
            .append(" department.short_name AS department_short_name, department.supervisor AS department_supervisor,")
            .append(" department.contact_number AS department_contact_number, organization.id AS organization_id ,")
            .append(" organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,\n")
            .append("organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number\n")
            .append("FROM department INNER JOIN organization ON department.organization_id=organization.id").toString();
    /**
     * Запрос на получение объекта по id из таблицы department
     */
    public static final String DEPARTMENT_GET_BY_ID_QUERY = new StringBuilder()
            .append(DEPARTMENT_GET_ALL_QUERY)
            .append(" WHERE department.id=?").toString();
    /**
     * Запрос на удаление всех записей в таблице department
     */
    public static final String DEPARTMENT_DELETE_ALL_QUERY = "DELETE FROM department";

    /**
     * Запрос на удаление записи по id в таблице department
     */
    public static final String DEPARTMENT_DELETE_BY_ID_QUERY = "DELETE FROM department WHERE id=?";

    /**
     * Запрос на обновление записи по id в таблице department
     */
    public static final String DEPARTMENT_UPDATE_QUERY = "UPDATE department SET full_name=?, short_name=?, supervisor=?, contact_number=?, organization_id=? WHERE id=?";

    /**
     * Запрос на создание записи в таблице jobTittle
     */
    public static final String JOB_TITTLE_CREATE_QUERY = "INSERT INTO job_tittle VALUES (?,?)";
    /**
     * Запрос на получение всех объектов из таблицы jobTittle
     */
    public static final String JOB_TITTLE_GET_ALL_QUERY = "SELECT job_tittle.id AS job_tittle_id, job_tittle.name AS job_name FROM job_tittle";
    /**
     * Запрос на получение объекта по id из таблицы jobTittle
     */
    public static final String JOB_TITTLE_GET_BY_ID_QUERY = "SELECT job_tittle.id AS job_tittle_id, job_tittle.name AS job_name FROM job_tittle WHERE id=?";
    /**
     * Запрос на удаление всех записей в таблице jobTittle
     */
    public static final String JOB_TITTLE_DELETE_ALL_QUERY = "DELETE FROM job_tittle";

    /**
     * Запрос на удаление записи по id в таблице jobTittle
     */
    public static final String JOB_TITTLE_DELETE_BY_ID_QUERY = "DELETE FROM job_tittle WHERE id=?";
    /**
     * Запрос на обновление записи по id в таблице jobTittle
     */
    public static final String JOB_TITTLE_UPDATE_ID_QUERY = "UPDATE job_tittle SET name=? WHERE id=?";

    /**
     * Запрос на создание записи в таблице organization
     */
    public static final String ORGANIZATION_CREATE_QUERY = "INSERT INTO organization VALUES (?,?,?,?,?)";
    /**
     * Запрос на получение всех объектов из таблицы organization
     */
    public static final String ORGANIZATION_GET_ALL_QUERY = new StringBuilder()
            .append("SELECT organization.id AS organization_id ,")
            .append("organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,")
            .append("organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number ")
            .append("FROM organization").toString();

    /**
     * Запрос на получение объекта по id из таблицы organization
     */
    public static final String ORGANIZATION_GET_BY_ID_QUERY = new StringBuilder(ORGANIZATION_GET_ALL_QUERY)
            .append(" WHERE organization.id=?").toString();
    /**
     * Запрос на удаление всех записей в таблице organization
     */
    public static final String ORGANIZATION_DELETE_ALL_QUERY = "DELETE FROM organization";
    /**
     * Запрос на удаление записи по id в таблице organization
     */
    public static final String ORGANIZATION_DELETE_BY_ID_QUERY = "DELETE FROM organization WHERE id=?";

    /**
     * Запрос на обновление записи по id в таблице organization
     */
    public static final String ORGANIZATION_UPDATE_QUERY = "UPDATE organization SET full_name=?, short_name=?, supervisor=?, contact_number=? WHERE id=?";


    /**
     * Запрос на создание записи в таблице person
     */
    public static final String PERSON_CREATE_QUERY = "INSERT INTO person VALUES (?,?,?,?,?,?,?,?,?)";
    /**
     * Запрос на получение всех объектов из таблицы person
     */
    public static final String PERSON_GET_ALL_QUERY = new StringBuilder()
            .append("SELECT  person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name,")
            .append("person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number,")
            .append("person.birth_day AS person_birth_day,")
            .append("department.id AS department_id, department.full_name AS department_full_name,")
            .append("department.short_name AS department_short_name, department.supervisor AS department_supervisor,")
            .append("department.contact_number AS department_contact_number, organization.id AS organization_id ,")
            .append("organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,")
            .append("organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number, ")
            .append("job_tittle.id AS job_tittle_id, job_tittle.name AS job_name  ")
            .append("FROM person ")
            .append("INNER JOIN department ")
            .append("    ON person.department_id=department_id ")
            .append("INNER JOIN job_tittle ")
            .append("    ON person.job_tittle_id=job_tittle.id ")
            .append("INNER JOIN organization ")
            .append("   ON organization.id=department.organization_id ").toString();
    /**
     * Запрос на получение объекта по id из таблицы person
     */
    public static final String PERSON_GET_BY_ID_QUERY = new StringBuilder(PERSON_GET_ALL_QUERY)
            .append(" WHERE person.id=?").toString();
    /**
     * Запрос на обновление записи в таблице person
     */
    public static final String PERSON_UPDATE_QUERY = "UPDATE person SET first_name=?, second_name=?, last_name=?, photo=?, job_tittle_id=?, department_id=?, phone_number=?, birth_day=? WHERE id=?";
    /**
     * Запрос на удаление всех записей в таблице person
     */
    public static final String PERSON_DELETE_ALL_QUERY = "DELETE FROM person";
    /**
     * Запрос на удаление записи по id в таблице person
     */
    public static final String PERSON_DELETE_BY_ID_QUERY = "DELETE FROM person WHERE id=?";

}
