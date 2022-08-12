package com.example.testproject1.queryholder.incomingdocumentquery;

/**
 * Класс для хранения SQL запросов IncomingDocument сущностей
 *
 * @author smigranov
 */
public class IncomingDocumentQueryHolder {
    /**
     * Запрос на создание записи в таблице incoming_document
     */
    public static final String INCOMING_DOCUMENT_CREATE_QUERY = "INSERT INTO incoming_document VALUES (?,?,?,?,?)";

    /**
     * Запрос на получение всех объектов из таблицы incoming_document
     */
    public static final String INCOMING_DOCUMENT_GET_ALL_QUERY = new StringBuilder()
            .append(" SELECT   base_document.id AS base_document_id,  ")
            .append(" base_document.name AS base_document_name, base_document.text AS base_document_text, ")
            .append(" base_document.reg_number AS base_document_number, base_document.creating_date AS base_document_date, ")
            .append(" person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name,")
            .append(" person.last_name AS person_last_name, ")
            .append(" incoming_document.number AS incoming_document_number, ")
            .append(" incoming_document.date_of_registration AS incoming_document_date_of_registration, sender.id AS person_sender_id, ")
            .append(" sender.first_name AS person_sender_first_name, sender.second_name AS person_sender_second_name,")
            .append(" sender.last_name AS person_sender_last_name,")
            .append(" destination.id AS person_destination_id, destination.first_name AS person_destination_first_name,")
            .append(" destination.second_name AS person_destination_second_name, destination.last_name AS person_destination_last_name  ")
            .append(" FROM incoming_document  ")
            .append(" INNER JOIN base_document  ")
            .append("     ON base_document.id=incoming_document.base_document_id ")
            .append(" INNER JOIN person  ")
            .append("     ON base_document.author_id=person.id ")
            .append(" INNER JOIN person AS sender ")
            .append("     ON incoming_document.sender_id=sender.id ")
            .append(" INNER JOIN person AS destination ")
            .append("     ON incoming_document.destination_id=destination.id ").toString();
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
}
