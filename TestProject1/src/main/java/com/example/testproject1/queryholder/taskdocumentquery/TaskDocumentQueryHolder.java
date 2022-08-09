package com.example.testproject1.queryholder.taskdocumentquery;
/**
 * Класс для хранения SQL запросов TaskDocument
 *
 * @author smigranov
 */
public class TaskDocumentQueryHolder {
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
            .append("                    person.last_name AS person_last_name, ")
            .append("                    response.id AS person_response_id,")
            .append("                    response.first_name AS person_response_first_name,")
            .append("                    response.second_name AS person_response_second_name,")
            .append("                    response.last_name AS person_response_last_name,")
            .append("                    controlPerson.id AS person_control_id,")
            .append("                    controlPerson.first_name AS person_control_first_name,")
            .append("                    controlPerson.second_name AS person_control_second_name,")
            .append("                    controlPerson.last_name AS person_control_last_name ")
            .append("                    FROM task_document")
            .append("                    INNER JOIN base_document")
            .append("                        ON base_document.id=task_document.base_document_id ")
            .append("                    INNER JOIN person ")
            .append("                        ON base_document.author_id=person.id ")
            .append("                    INNER JOIN person AS response ")
            .append("                        ON task_document.responsible_id=response.id  ")
            .append("                    INNER JOIN person AS controlPerson ")
            .append("                        ON task_document.control_person_id=controlPerson.id   ").toString();
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
