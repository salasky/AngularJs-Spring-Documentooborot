package com.example.testproject1.dao.queryholder;

/**
 * Класс для хранения SQL запросов
 *
 * @author smigranov
 */
public class QueryHolder {
    /**
     * Запрос на создание записи в таблице base_document
     */
    public static final String BASE_DOCUMENT_CREATE_QUERY = "INSERT INTO base_document VALUES (?,?,?,?,?,?)";

    /**
     * Запрос на получение всех объектов из таблицы base_document
     */
    public static final String BASE_DOCUMENT_GET_ALL_QUERY = new StringBuilder(
            "SELECT  base_document.id AS base_document_id, base_document.name AS base_document_name, ")
            .append("base_document.text AS base_document_text, base_document.reg_number AS base_document_number,")
            .append("base_document.creating_date AS base_document_date,")
            .append("person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name,")
            .append("person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number,")
            .append("person.birth_day AS person_birth_day,")
            .append("department.id AS department_id, department.full_name AS department_full_name,")
            .append("department.short_name AS department_short_name, department.supervisor AS department_supervisor,")
            .append("department.contact_number AS department_contact_number, organization.id AS organization_id ,")
            .append("organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,")
            .append("organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number, ")
            .append("job_tittle.id AS job_tittle_id, job_tittle.name AS job_name ")
            .append("FROM base_document ")
            .append("INNER JOIN person ")
            .append("ON base_document.author_id=person.id ")
            .append("INNER JOIN department ")
            .append("ON person.department_id=department_id ")
            .append("INNER JOIN job_tittle ")
            .append("ON person.job_tittle_id=job_tittle.id ")
            .append("INNER JOIN organization ")
            .append("ON organization.id=department.organization_id").toString();
    /**
     * Запрос на получение объекта по id из таблицы base_document
     */
    public static final String BASE_DOCUMENT_GET_BY_ID_QUERY = new StringBuilder(BASE_DOCUMENT_GET_ALL_QUERY)
            .append(" WHERE base_document.reg_number=?").toString();
    /**
     * Запрос на обновление записи в таблице base_document
     */
    public static final String BASE_DOCUMENT_UPDATE_QUERY = "UPDATE base_document SET name=?, text=?, reg_number=?, creating_date=?, author_id=? WHERE id=?";
    /**
     * Запрос на удаление всех записей в таблице base_document
     */
    public static final String BASE_DOCUMENT_DELETE_ALL_QUERY = "DELETE FROM base_document";
    /**
     * Запрос на удаление записи по id в таблице base_document
     */
    public static final String BASE_DOCUMENT_DELETE_BY_ID_QUERY = "DELETE FROM base_document WHERE id=?";
    /**
     * Запрос на обновление записи в таблице base_document
     */
    public static final String BASE_DOCUMENT_EXIST_BY_REG_NUMBER_QUERY = new StringBuilder(BASE_DOCUMENT_GET_ALL_QUERY)
            .append(" WHERE base_document.reg_number=?").toString();

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
     * Запрос на создание записи в таблице incoming_document
     */
    public static final String INCOMING_DOCUMENT_CREATE_QUERY = "INSERT INTO incoming_document VALUES (?,?,?,?,?)";
    /**
     * Запрос на получение всех объектов из таблицы incoming_document
     */
    public static final String INCOMING_DOCUMENT_GET_ALL_QUERY = new StringBuilder()
            .append("                    SELECT   base_document.id AS base_document_id,  ")
            .append("                    base_document.name AS base_document_name, base_document.text AS base_document_text, ")
            .append("                    base_document.reg_number AS base_document_number, base_document.creating_date AS base_document_date, ")
            .append("                    person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name,  ")
            .append("                    person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number,  ")
            .append("                    person.birth_day AS person_birth_day, department.id AS department_id, department.full_name AS department_full_name,  ")
            .append("                    department.short_name AS department_short_name, department.supervisor AS department_supervisor,  ")
            .append("                    department.contact_number AS department_contact_number, organization.id AS organization_id ,  ")
            .append("                    organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,  ")
            .append("                    organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number,   ")
            .append("                    job_tittle.id AS job_tittle_id, job_tittle.name AS job_name, incoming_document.number AS incoming_document_number, ")
            .append("                    incoming_document.date_of_registration AS incoming_document_date_of_registration, sender.id AS person_sender_id, ")
            .append("                    sender.first_name AS person_sender_first_name, sender.second_name AS person_sender_second_name,  ")
            .append("                    sender.last_name AS person_sender_last_name, sender.photo AS person_sender_photo, ")
            .append("                    sender.phone_number AS person_sender_phone_number, sender.birth_day AS person_sender_birth_day,  ")
            .append("                    departmentsender.id AS department_sender_id, departmentsender.full_name AS department_sender_full_name,  ")
            .append("                    departmentsender.short_name AS department_sender_short_name, departmentsender.supervisor AS department_sender_supervisor,  ")
            .append("                    departmentsender.contact_number AS department_sender_contact_number, organizationsender.id AS organization_sender_id ,  ")
            .append("                    organizationsender.full_name AS organization_sender_full_name, organizationsender.short_name AS organization_sender_short_name,  ")
            .append("                    organizationsender.supervisor AS organization_sender_supervisor, organizationsender.contact_number AS organization_sender_contact_number,   ")
            .append("                    jobTittlesender.id AS job_tittle_sender_id,jobTittlesender.name AS job_sender_name, ")
            .append("                    destination.id AS person_destination_id, destination.first_name AS person_destination_first_name,  ")
            .append("                    destination.second_name AS person_destination_second_name, destination.last_name AS person_destination_last_name,  ")
            .append("                    destination.photo AS person_destination_photo, destination.phone_number AS person_destination_phone_number, ")
            .append("                    destination.birth_day AS person_destination_birth_day, departmentDestination.id AS department_destination_id, ")
            .append("                    departmentDestination.full_name AS department_destination_full_name, departmentDestination.short_name AS department_destination_short_name, ")
            .append("                    departmentDestination.supervisor AS department_destination_supervisor, departmentDestination.contact_number AS department_destination_contact_number, ")
            .append("                    organizationDestination.id AS organization_destination_id , organizationDestination.full_name AS organization_destination_full_name,  ")
            .append("                    organizationDestination.short_name AS organization_destination_short_name, organizationDestination.supervisor AS organization_destination_supervisor, ")
            .append("                    organizationDestination.contact_number AS organization_destination_contact_number, jobTittleDestination.id AS job_tittle_destination_id, ")
            .append("                    jobTittleDestination.name AS job_destination_name ")
            .append("                    FROM incoming_document  ")
            .append("                    INNER JOIN base_document  ")
            .append("                        ON base_document.id=incoming_document.base_document_id ")
            .append("                    INNER JOIN person  ")
            .append("                        ON base_document.author_id=person.id   ")
            .append("                    INNER JOIN department   ")
            .append("                        ON person.department_id=department_id   ")
            .append("                    INNER JOIN job_tittle   ")
            .append("                        ON person.job_tittle_id=job_tittle.id  ")
            .append("                    INNER JOIN organization   ")
            .append("                       ON organization.id=department.organization_id ")
            .append("                    INNER JOIN person AS sender ")
            .append("                        ON incoming_document.sender_id=sender.id ")
            .append("                    INNER JOIN department AS departmentSender ")
            .append("                        ON sender.department_id=departmentSender.id   ")
            .append("                    INNER JOIN organization AS organizationSender ")
            .append("                       ON organizationSender.id=departmentSender.organization_id ")
            .append("                    INNER JOIN job_tittle AS jobTittleSender ")
            .append("                        ON sender.job_tittle_id=jobTittleSender.id  ")
            .append("                    INNER JOIN person AS destination ")
            .append("                        ON incoming_document.destination_id=destination.id ")
            .append("                    INNER JOIN department AS departmentDestination ")
            .append("                        ON destination.department_id=departmentDestination.id  ")
            .append("                    INNER JOIN organization AS organizationDestination ")
            .append("                       ON organizationDestination.id=departmentDestination.organization_id  ")
            .append("                    INNER JOIN job_tittle AS jobTittleDestination ")
            .append("                        ON destination.job_tittle_id=jobTittleDestination.id ").toString();
    /**
     * Запрос на получение объекта по id из таблицы incoming_document
     */
    public static final String INCOMING_DOCUMENT_GET_BY_ID_QUERY = new StringBuilder(INCOMING_DOCUMENT_GET_ALL_QUERY)
            .append(" WHERE incoming_document.base_document_id=?").toString();
    /**
     * Запрос на обновление записи в таблице incoming_document
     */
    public static final String INCOMING_DOCUMENT_UPDATE_QUERY = "UPDATE incoming_document SET sender_id=?, destination_id=?, number=?, date_of_registration=? WHERE incoming_document.base_document_id=?";
    /**
     * Запрос на удаление всех записей в таблице incoming_document
     */
    public static final String INCOMING_DOCUMENT_DELETE_ALL_QUERY = "DELETE FROM incoming_document";
    /**
     * Запрос на удаление записи по id в таблице incoming_document
     */
    public static final String INCOMING_DOCUMENT_DELETE_BY_ID_QUERY = "DELETE FROM incoming_document WHERE base_document_id=?";
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
     * Запрос на создание записи в таблице outgoing_document
     */
    public static final String OUTGOING_DOCUMENT_CREATE_QUERY = "INSERT INTO outgoing_document VALUES (?,?,?)";

    /**
     * Запрос на получение всех объектов из таблицы outgoing_document
     */
    public static final String OUTGOING_DOCUMENT_GET_ALL_QUERY = new StringBuilder()
            .append("                    SELECT   base_document.id AS base_document_id,  ")
            .append("                    base_document.name AS base_document_name,  ")
            .append("                    base_document.text AS base_document_text,  ")
            .append("                    base_document.reg_number AS base_document_number,   ")
            .append("                    base_document.creating_date AS base_document_date,  ")
            .append("                    person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name,   ")
            .append("                    person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number,   ")
            .append("                    person.birth_day AS person_birth_day,   ")
            .append("                    department.id AS department_id, department.full_name AS department_full_name,   ")
            .append("                    department.short_name AS department_short_name, department.supervisor AS department_supervisor,   ")
            .append("                    department.contact_number AS department_contact_number, organization.id AS organization_id ,   ")
            .append("                    organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,   ")
            .append("                    organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number,    ")
            .append("                    job_tittle.id AS job_tittle_id, job_tittle.name AS job_name,  ")
            .append("                    outgoing_document.delivery_type AS outgoing_delivery_type,  ")
            .append("                    sender.id AS person_sender_id,  ")
            .append("                    sender.first_name AS person_sender_first_name,   ")
            .append("                    sender.second_name AS person_sender_second_name,   ")
            .append("                    sender.last_name AS person_sender_last_name,   ")
            .append("                    sender.photo AS person_sender_photo,  ")
            .append("                    sender.phone_number AS person_sender_phone_number,  ")
            .append("                    sender.birth_day AS person_sender_birth_day,   ")
            .append("                    departmentsender.id AS department_sender_id,  ")
            .append("                    departmentsender.full_name AS department_sender_full_name,   ")
            .append("                    departmentsender.short_name AS department_sender_short_name,  ")
            .append("                    departmentsender.supervisor AS department_sender_supervisor,   ")
            .append("                    departmentsender.contact_number AS department_sender_contact_number,  ")
            .append("                    organizationsender.id AS organization_sender_id ,   ")
            .append("                    organizationsender.full_name AS organization_sender_full_name,   ")
            .append("                    organizationsender.short_name AS organization_sender_short_name,   ")
            .append("                    organizationsender.supervisor AS organization_sender_supervisor,  ")
            .append("                    organizationsender.contact_number AS organization_sender_contact_number,    ")
            .append("                    jobTittlesender.id AS job_tittle_sender_id,  ")
            .append("                    jobTittlesender.name AS job_sender_name  ")
            .append("                    FROM outgoing_document   ")
            .append("                    INNER JOIN base_document   ")
            .append("                        ON base_document.id=outgoing_document.base_document_id  ")
            .append("                    INNER JOIN person   ")
            .append("                        ON base_document.author_id=person.id    ")
            .append("                    INNER JOIN department    ")
            .append("                        ON person.department_id=department_id    ")
            .append("                    INNER JOIN job_tittle    ")
            .append("                        ON person.job_tittle_id=job_tittle.id   ")
            .append("                    INNER JOIN organization    ")
            .append("                       ON organization.id=department.organization_id  ")
            .append("                    INNER JOIN person AS sender  ")
            .append("                        ON outgoing_document.sender_id=sender.id  ")
            .append("                    INNER JOIN department AS departmentSender  ")
            .append("                        ON sender.department_id=departmentSender.id    ")
            .append("                    INNER JOIN organization AS organizationSender  ")
            .append("                       ON organizationSender.id=departmentSender.organization_id  ")
            .append("                    INNER JOIN job_tittle AS jobTittleSender  ")
            .append("                        ON sender.job_tittle_id=jobTittleSender.id ").toString();
    /**
     * Запрос на получение объекта по id из таблицы outgoing_document
     */
    public static final String OUTGOING_DOCUMENT_GET_BY_ID_QUERY = new StringBuilder(OUTGOING_DOCUMENT_GET_ALL_QUERY)
            .append(" WHERE outgoing_document.base_document_id=?").toString();
    /**
     * Запрос на обновление записи в таблице outgoing_document
     */
    public static final String OUTGOING_DOCUMENT_UPDATE_QUERY = "UPDATE outgoing_document SET sender_id=?, delivery_type=? WHERE outgoing_document.base_document_id=?";
    /**
     * Запрос на удаление всех записей в таблице outgoing_document
     */
    public static final String OUTGOING_DOCUMENT_DELETE_ALL_QUERY = "DELETE FROM outgoing_document";
    /**
     * Запрос на удаление записи по id в таблице outgoing_document
     */
    public static final String OUTGOING_DOCUMENT_DELETE_BY_ID_QUERY = "DELETE FROM outgoing_document WHERE base_document_id=?";

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
    /**
     * Запрос на создание записи в таблице task_document
     */
    public static final String TASK_DOCUMENT_CREATE_QUERY = "INSERT INTO task_document VALUES (?,?,?,?,?,?)";
    /**
     * Запрос на получение всех объектов из таблицы task_document
     */
    public static final String TASK_DOCUMENT_GET_ALL_QUERY = new StringBuilder()
            .append("                    SELECT   task_document.base_document_id AS task_document_id,")
            .append("                    task_document.out_date AS task_document_out_date,")
            .append("                    task_document.exec_period AS task_document_exec_period,")
            .append("                    task_document.sign_of_control AS task_document_sign_of_control,")
            .append("                    base_document.id AS base_document_id, base_document.name AS base_document_name,")
            .append("                    base_document.text AS base_document_text, base_document.reg_number AS base_document_number,")
            .append("                    base_document.creating_date AS base_document_date,")
            .append("                    person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name,")
            .append("                    person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number,")
            .append("                    person.birth_day AS person_birth_day,")
            .append("                    department.id AS department_id, department.full_name AS department_full_name,")
            .append("                    department.short_name AS department_short_name, department.supervisor AS department_supervisor,")
            .append("                    department.contact_number AS department_contact_number, organization.id AS organization_id ,")
            .append("                    organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,")
            .append("                    organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number,")
            .append("                    job_tittle.id AS job_tittle_id, job_tittle.name AS job_name,")
            .append("                    response.id AS person_response_id,")
            .append("                    response.first_name AS person_response_first_name,")
            .append("                    response.second_name AS person_response_second_name,")
            .append("                    response.last_name AS person_response_last_name,")
            .append("                    response.photo AS person_response_photo,")
            .append("                    response.phone_number AS person_response_phone_number,")
            .append("                    response.birth_day AS person_response_birth_day,")
            .append("                    departmentResponse.id AS department_response_id,")
            .append("                    departmentResponse.full_name AS department_response_full_name,")
            .append("                    departmentResponse.short_name AS department_response_short_name,")
            .append("                    departmentResponse.supervisor AS department_response_supervisor,")
            .append("                    departmentResponse.contact_number AS department_response_contact_number,")
            .append("                    organizationResponse.id AS organization_response_id ,")
            .append("                    organizationResponse.full_name AS organization_response_full_name,")
            .append("                    organizationResponse.short_name AS organization_response_short_name,")
            .append("                    organizationResponse.supervisor AS organization_response_supervisor,")
            .append("                    organizationResponse.contact_number AS organization_response_contact_number,")
            .append("                    jobTittleResponse.id AS job_tittle_response_id,")
            .append("                    jobTittleResponse.name AS job_response_name,")
            .append("                    controlPerson.id AS person_control_id,")
            .append("                    controlPerson.first_name AS person_control_first_name,")
            .append("                    controlPerson.second_name AS person_control_second_name,")
            .append("                    controlPerson.last_name AS person_control_last_name,")
            .append("                    controlPerson.photo AS person_control_photo,")
            .append("                    controlPerson.phone_number AS person_control_phone_number,")
            .append("                    controlPerson.birth_day AS person_control_birth_day,")
            .append("                    departmentControl.id AS department_control_id,")
            .append("                    departmentControl.full_name AS department_control_full_name,")
            .append("                    departmentControl.short_name AS department_control_short_name,")
            .append("                    departmentControl.supervisor AS department_control_supervisor, ")
            .append("                    departmentControl.contact_number AS department_control_contact_number,")
            .append("                    organizationControl.id AS organization_control_id ,")
            .append("                    organizationControl.full_name AS organization_control_full_name,")
            .append("                    organizationControl.short_name AS organization_control_short_name,")
            .append("                    organizationControl.supervisor AS organization_control_supervisor,")
            .append("                    organizationControl.contact_number AS organization_control_contact_number,")
            .append("                    jobTittleControl.id AS job_tittle_control_id,")
            .append("                    jobTittleControl.name AS job_control_name")
            .append("                    FROM task_document")
            .append("                    INNER JOIN base_document")
            .append("                        ON base_document.id=task_document.base_document_id ")
            .append("                    INNER JOIN person ")
            .append("                        ON base_document.author_id=person.id ")
            .append("                    INNER JOIN department  ")
            .append("                        ON person.department_id=department_id ")
            .append("                    INNER JOIN job_tittle  ")
            .append("                        ON person.job_tittle_id=job_tittle.id ")
            .append("                    INNER JOIN organization  ")
            .append("                       ON organization.id=department.organization_id ")
            .append("                    INNER JOIN person AS response ")
            .append("                        ON task_document.responsible_id=response.id  ")
            .append("                    INNER JOIN department AS departmentResponse ")
            .append("                        ON response.department_id=departmentResponse.id ")
            .append("                    INNER JOIN organization AS organizationResponse")
            .append("                       ON organizationResponse.id=departmentResponse.organization_id ")
            .append("                    INNER JOIN job_tittle AS jobTittleResponse ")
            .append("                        ON response.job_tittle_id=jobTittleResponse.id  ")
            .append("                    INNER JOIN person AS controlPerson ")
            .append("                        ON task_document.control_person_id=controlPerson.id   ")
            .append("                    INNER JOIN department AS departmentControl ")
            .append("                        ON controlPerson.department_id=departmentControl.id  ")
            .append("                    INNER JOIN organization AS organizationControl ")
            .append("                       ON organizationControl.id=departmentControl.organization_id ")
            .append("                    INNER JOIN job_tittle AS jobTittleControl ")
            .append("                        ON controlPerson.job_tittle_id=jobTittleControl.id ").toString();
    /**
     * Запрос на получение объекта по id из таблицы task_document
     */
    public static final String TASK_DOCUMENT_GET_BY_ID_QUERY = new StringBuilder(TASK_DOCUMENT_GET_ALL_QUERY)
            .append(" WHERE task_document.base_document_id=?").toString();
    /**
     * Запрос на обновление записи в таблице task_document
     */
    public static final String TASK_DOCUMENT_UPDATE_QUERY = "UPDATE task_document SET out_date=?, exec_period=?, responsible_id=?, sign_of_control=?, control_person_id=? WHERE task_document.base_document_id=?";
    /**
     * Запрос на удаление всех записей в таблице task_document
     */
    public static final String TASK_DOCUMENT_DELETE_ALL_QUERY = "DELETE FROM task_document";
    /**
     * Запрос на удаление записи по id в таблице task_document
     */
    public static final String TASK_DOCUMENT_DELETE_BY_ID_QUERY = "DELETE FROM task_document WHERE base_document_id=?";
}
