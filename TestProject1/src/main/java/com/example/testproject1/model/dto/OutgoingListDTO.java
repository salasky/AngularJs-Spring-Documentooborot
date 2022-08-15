package com.example.testproject1.model.dto;

import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для сохранения List документов в базу данных
 *
 * @author smigranov
 */
public class OutgoingListDTO {
    /**
     * List OutgoingDocument
     */
    private final List<OutgoingDocument> outgoingDocuments = new ArrayList<>();

    /**
     * Метод получения списка OutgoingDocument
     *
     * @return {@link List} объектов {@link OutgoingDocument}
     */
    @JsonProperty("list")
    public List<OutgoingDocument> getOutgoingDocuments() {
        return outgoingDocuments;
    }
}
