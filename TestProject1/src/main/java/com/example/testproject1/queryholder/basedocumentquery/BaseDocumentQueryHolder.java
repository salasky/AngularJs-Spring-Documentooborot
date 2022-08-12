package com.example.testproject1.queryholder.basedocumentquery;

/**
 * Класс для хранения SQL запросов BaseDocument
 *
 * @author smigranov
 */
public class BaseDocumentQueryHolder {
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
            .append("base_document.author_id AS person_id ")
            .append("FROM base_document ").toString();
    /**
     * Запрос на получение объекта по id из таблицы base_document
     */
    public static final String BASE_DOCUMENT_GET_BY_ID_QUERY = new StringBuilder(BASE_DOCUMENT_GET_ALL_QUERY)
            .append(" WHERE base_document.id=?").toString();
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
}
