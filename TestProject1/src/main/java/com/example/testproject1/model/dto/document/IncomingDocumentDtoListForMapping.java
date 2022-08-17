package com.example.testproject1.model.dto.document;

import com.example.testproject1.model.document.IncomingDocument;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для сохранения List документов в базу данных
 *
 * @author smigranov
 */
public class IncomingDocumentDtoListForMapping {
    /**
     * List IncomingDocument
     */
    private final List<IncomingDocumentDTO> incomingDocumentList = new ArrayList<>();

    /**
     * Метод получения списка IncomingDocument
     *
     * @return {@link List} объектов {@link IncomingDocument}
     */
    @JsonProperty("list")
    public List<IncomingDocumentDTO> getIncomingDocumentList() {
        return incomingDocumentList;
    }
}
