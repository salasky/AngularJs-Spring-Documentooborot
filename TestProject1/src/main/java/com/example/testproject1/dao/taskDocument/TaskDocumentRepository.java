package com.example.testproject1.dao.taskDocument;

import com.example.testproject1.dao.baseDocument.mapper.BaseDocumentMapper;
import com.example.testproject1.dao.taskDocument.mapper.TaskDocumentMapper;
import com.example.testproject1.model.documents.BaseDocument;
import com.example.testproject1.model.documents.TaskDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskDocumentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * Запрос на создание записи в таблице task_document
     */
    private final String queryCreate="INSERT INTO task_document VALUES (?,?,?,?,?,?)";
    /**
     * Запрос на получение всех объектов из таблицы task_document
     */
    private final String queryGetAll="SELECT   task_document.base_document_id AS task_document_id," +
            "                    task_document.out_date AS task_document_out_date," +
            "                    task_document.exec_period AS task_document_exec_period," +
            "                    task_document.sign_of_control AS task_document_sign_of_control,"+
            "                    base_document.id AS base_document_id, base_document.name AS base_document_name," +
            "                    base_document.text AS base_document_text, base_document.reg_number AS base_document_number," +
            "                    base_document.creating_date AS base_document_date," +
            "                    person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name," +
            "                    person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number," +
            "                    person.birth_day AS person_birth_day," +
            "                    department.id AS department_id, department.full_name AS department_full_name," +
            "                    department.short_name AS department_short_name, department.supervisor AS department_supervisor," +
            "                    department.contact_number AS department_contact_number, organization.id AS organization_id ," +
            "                    organization.full_name AS organization_full_name, organization.short_name AS organization_short_name," +
            "                    organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number," +
            "                    job_tittle.id AS job_tittle_id, job_tittle.name AS job_name," +
            "                    response.id AS person_response_id," +
            "                    response.first_name AS person_response_first_name," +
            "                    response.second_name AS person_response_second_name," +
            "                    response.last_name AS person_response_last_name," +
            "                    response.photo AS person_response_photo," +
            "                    response.phone_number AS person_response_phone_number," +
            "                    response.birth_day AS person_response_birth_day," +
            "                    departmentResponse.id AS department_response_id," +
            "                    departmentResponse.full_name AS department_response_full_name," +
            "                    departmentResponse.short_name AS department_response_short_name," +
            "                    departmentResponse.supervisor AS department_response_supervisor," +
            "                    departmentResponse.contact_number AS department_response_contact_number," +
            "                    organizationResponse.id AS organization_response_id ," +
            "                    organizationResponse.full_name AS organization_response_full_name," +
            "                    organizationResponse.short_name AS organization_response_short_name," +
            "                    organizationResponse.supervisor AS organization_response_supervisor," +
            "                    organizationResponse.contact_number AS organization_response_contact_number," +
            "                    jobTittleResponse.id AS job_tittle_response_id," +
            "                    jobTittleResponse.name AS job_response_name," +
            "                    controlPerson.id AS person_control_id," +
            "                    controlPerson.first_name AS person_control_id_first_name," +
            "                    controlPerson.second_name AS person_control_id_second_name," +
            "                    controlPerson.last_name AS person_control_id_last_name," +
            "                    controlPerson.photo AS person_control_id_photo," +
            "                    controlPerson.phone_number AS person_control_id_phone_number," +
            "                    controlPerson.birth_day AS person_control_id_birth_day," +
            "                    departmentControl.id AS department_control_id," +
            "                    departmentControl.full_name AS department_control_full_name," +
            "                    departmentControl.short_name AS department_control_short_name," +
            "                    departmentControl.supervisor AS department_control_supervisor, " +
            "                    departmentControl.contact_number AS department_control_contact_number," +
            "                    organizationControl.id AS organization_control_id ," +
            "                    organizationControl.full_name AS organization_control_full_name," +
            "                    organizationControl.short_name AS organization_control_short_name," +
            "                    organizationControl.supervisor AS organization_control_supervisor," +
            "                    organizationControl.contact_number AS organization_control_contact_number," +
            "                    jobTittleControl.id AS job_tittle_control_id," +
            "                    jobTittleControl.name AS job_control_name" +
            "                    FROM task_document" +
            "                    INNER JOIN base_document" +
            "                        ON base_document.id=task_document.base_document_id " +
            "                    INNER JOIN person " +
            "                        ON base_document.author_id=person.id " +
            "                    INNER JOIN department  " +
            "                        ON person.department_id=department_id " +
            "                    INNER JOIN job_tittle  " +
            "                        ON person.job_tittle_id=job_tittle.id " +
            "                    INNER JOIN organization  " +
            "                       ON organization.id=department.organization_id " +
            "                    INNER JOIN person AS response " +
            "                        ON task_document.responsible_id=response.id  " +
            "                    INNER JOIN department AS departmentResponse " +
            "                        ON response.department_id=departmentResponse.id " +
            "                    INNER JOIN organization AS organizationResponse" +
            "                       ON organizationResponse.id=departmentResponse.organization_id " +
            "                    INNER JOIN job_tittle AS jobTittleResponse " +
            "                        ON response.job_tittle_id=jobTittleResponse.id  " +
            "                    INNER JOIN person AS controlPerson " +
            "                        ON task_document.control_person_id=controlPerson.id   " +
            "                    INNER JOIN department AS departmentControl " +
            "                        ON controlPerson.department_id=departmentControl.id  " +
            "                    INNER JOIN organization AS organizationControl " +
            "                       ON organizationControl.id=departmentControl.organization_id " +
            "                    INNER JOIN job_tittle AS jobTittleControl " +
            "                        ON controlPerson.job_tittle_id=jobTittleControl.id ";
    /**
     * Запрос на получение объекта по id из таблицы task_document
     */
    private final String queryGetById="SELECT   task_document.base_document_id AS task_document_id," +
            "                    task_document.out_date AS task_document_out_date," +
            "                    task_document.exec_period AS task_document_exec_period," +
            "                    task_document.sign_of_control AS task_document_sign_of_control,"+
            "                    base_document.id AS base_document_id, base_document.name AS base_document_name," +
            "                    base_document.text AS base_document_text, base_document.reg_number AS base_document_number," +
            "                    base_document.creating_date AS base_document_date," +
            "                    person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name," +
            "                    person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number," +
            "                    person.birth_day AS person_birth_day," +
            "                    department.id AS department_id, department.full_name AS department_full_name," +
            "                    department.short_name AS department_short_name, department.supervisor AS department_supervisor," +
            "                    department.contact_number AS department_contact_number, organization.id AS organization_id ," +
            "                    organization.full_name AS organization_full_name, organization.short_name AS organization_short_name," +
            "                    organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number," +
            "                    job_tittle.id AS job_tittle_id, job_tittle.name AS job_name," +
            "                    response.id AS person_response_id," +
            "                    response.first_name AS person_response_id_first_name," +
            "                    response.second_name AS person_response_id_second_name," +
            "                    response.last_name AS person_response_id_last_name," +
            "                    response.photo AS person_response_id_photo," +
            "                    response.phone_number AS person_response_id_phone_number," +
            "                    response.birth_day AS person_response_id_birth_day," +
            "                    departmentResponse.id AS department_response_id," +
            "                    departmentResponse.full_name AS department_response_full_name," +
            "                    departmentResponse.short_name AS department_response_short_name," +
            "                    departmentResponse.supervisor AS department_response_supervisor," +
            "                    departmentResponse.contact_number AS department_response_contact_number," +
            "                    organizationResponse.id AS organization_response_id ," +
            "                    organizationResponse.full_name AS organization_response_full_name," +
            "                    organizationResponse.short_name AS organization_response_short_name," +
            "                    organizationResponse.supervisor AS organization_response_supervisor," +
            "                    organizationResponse.contact_number AS organization_response_contact_number," +
            "                    jobTittleResponse.id AS job_tittle_response_id," +
            "                    jobTittleResponse.name AS job_response_name," +
            "                    controlPerson.id AS person_control_id," +
            "                    controlPerson.first_name AS person_control_id_first_name," +
            "                    controlPerson.second_name AS person_control_id_second_name," +
            "                    controlPerson.last_name AS person_control_id_last_name," +
            "                    controlPerson.photo AS person_control_id_photo," +
            "                    controlPerson.phone_number AS person_control_id_phone_number," +
            "                    controlPerson.birth_day AS person_control_id_birth_day," +
            "                    departmentControl.id AS department_control_id," +
            "                    departmentControl.full_name AS department_control_full_name," +
            "                    departmentControl.short_name AS department_control_short_name," +
            "                    departmentControl.supervisor AS department_control_supervisor, " +
            "                    departmentControl.contact_number AS department_control_contact_number," +
            "                    organizationControl.id AS organization_control_id ," +
            "                    organizationControl.full_name AS organization_control_full_name," +
            "                    organizationControl.short_name AS organization_control_short_name," +
            "                    organizationControl.supervisor AS organization_control_supervisor," +
            "                    organizationControl.contact_number AS organization_control_contact_number," +
            "                    jobTittleControl.id AS job_tittle_control_id," +
            "                    jobTittleControl.name AS job_control_name" +
            "                    FROM task_document" +
            "                    INNER JOIN base_document" +
            "                        ON base_document.id=task_document.base_document_id " +
            "                    INNER JOIN person " +
            "                        ON base_document.author_id=person.id " +
            "                    INNER JOIN department  " +
            "                        ON person.department_id=department_id " +
            "                    INNER JOIN job_tittle  " +
            "                        ON person.job_tittle_id=job_tittle.id " +
            "                    INNER JOIN organization  " +
            "                       ON organization.id=department.organization_id " +
            "                    INNER JOIN person AS response " +
            "                        ON task_document.responsible_id=response.id  " +
            "                    INNER JOIN department AS departmentResponse " +
            "                        ON response.department_id=departmentResponse.id " +
            "                    INNER JOIN organization AS organizationResponse" +
            "                       ON organizationResponse.id=departmentResponse.organization_id " +
            "                    INNER JOIN job_tittle AS jobTittleResponse " +
            "                        ON response.job_tittle_id=jobTittleResponse.id  " +
            "                    INNER JOIN person AS controlPerson " +
            "                        ON task_document.control_person_id=controlPerson.id   " +
            "                    INNER JOIN department AS departmentControl " +
            "                        ON controlPerson.department_id=departmentControl.id  " +
            "                    INNER JOIN organization AS organizationControl " +
            "                       ON organizationControl.id=departmentControl.organization_id " +
            "                    INNER JOIN job_tittle AS jobTittleControl " +
            "                        ON controlPerson.job_tittle_id=jobTittleControl.id WHERE task_document.base_document_id=?";

    /**
     * Запрос на обновление записи в таблице task_document
     */
    private final String queryUpdate="UPDATE task_document SET out_date=?, exec_period=?, responsible_id=?," +
            " sign_of_control=?, control_person_id=? WHERE task_document.base_document_id=?";

    /**
     * Запрос на удаление всех записей в таблице task_document
     */
    private final String queryDeleteAll="DELETE FROM task_document";
    /**
     * Запрос на удаление записи по id в таблице task_document
     */
    private final String queryDeleteById="DELETE FROM task_document WHERE base_document_id=?";


    public void create(TaskDocument taskDocument){
        jdbcTemplate.update(queryCreate,taskDocument.getId().toString(),taskDocument.getOutDate()
                ,taskDocument.getExecPeriod(), taskDocument.getResponsible().getId().toString(),
                taskDocument.getSignOfControl(),taskDocument.getControlPerson().getId().toString());
    }
    public List<TaskDocument> getAll(){
        return jdbcTemplate.query(queryGetAll,new TaskDocumentMapper());
    }

    public Optional<TaskDocument> getById(String id){
        return jdbcTemplate.query(queryGetById, new TaskDocumentMapper(),id).stream().findFirst();
    }
    public Integer update(TaskDocument taskDocument){
        return jdbcTemplate.update(queryUpdate,taskDocument.getOutDate(),taskDocument.getExecPeriod(),
                taskDocument.getResponsible().getId().toString(), taskDocument.getSignOfControl(),
                taskDocument.getControlPerson().getId().toString(),taskDocument.getId().toString());
    }

    public void deleteAll(){
        jdbcTemplate.update(queryDeleteAll);
    }

    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(queryDeleteById, id);
        return update;
    }
}
