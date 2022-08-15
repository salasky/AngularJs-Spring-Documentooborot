package com.example.testproject1.model.dto;

import com.example.testproject1.model.document.IncomingDocument;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для сохранения List документов в базу данных
 *
 * @author smigranov
 */
public class IncomingListDTO {
    /**
     * List IncomingDocument
     */
    private final List<IncomingDocument> incomingDocumentList = new ArrayList<>();

    /**
     * Метод получения списка IncomingDocument
     *
     * @return {@link List} объектов {@link IncomingDocument}
     */
    @JsonProperty("list")
    public List<IncomingDocument> getIncomingDocumentList() {
        return incomingDocumentList;
    }
}
