package com.example.testproject1.queryholder.outgoingdocumentquery;

/**
 * Класс для хранения SQL запросов OutgoingDocument
 *
 * @author smigranov
 */
public class OutgoingDocumentQueryHolder {
    /**
     * Запрос на создание записи в таблице outgoing_document
     */
    public static final String OUTGOING_DOCUMENT_CREATE_QUERY = "INSERT INTO outgoing_document VALUES (?,?,?)";

    /**
     * Запрос на получение всех объектов из таблицы outgoing_document
     */
    public static final String OUTGOING_DOCUMENT_GET_ALL_QUERY = new StringBuilder()
            .append(" SELECT   base_document.id AS base_document_id, ")
            .append(" base_document.name AS base_document_name,  ")
            .append(" base_document.text AS base_document_text,  ")
            .append(" base_document.reg_number AS base_document_number, ")
            .append(" base_document.creating_date AS base_document_date, ")
            .append(" base_document.author_id AS person_id, ")
            .append(" outgoing_document.delivery_type AS outgoing_delivery_type, ")
            .append(" outgoing_document.sender_id AS person_sender_id ")
            .append(" FROM outgoing_document ")
            .append(" INNER JOIN base_document ")
            .append("     ON base_document.id=outgoing_document.base_document_id ").toString();
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
    public static final String OUTGOING_DOCUMENT_DELETE_ALL_QUERY = new StringBuilder("DELETE FROM base_document ")
            .append(" WHERE base_document.id IN (")
            .append(" SELECT base_document.id ")
            .append(" FROM base_document ")
            .append(" INNER JOIN outgoing_document  ")
            .append("     ON base_document.id=outgoing_document.base_document_id)").toString();
    /**
     * Запрос на удаление записи по id в таблице outgoing_document
     */
    public static final String OUTGOING_DOCUMENT_DELETE_BY_ID_QUERY = "DELETE FROM outgoing_document WHERE base_document_id=?";
}
