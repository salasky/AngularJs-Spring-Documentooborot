package com.example.testproject1.model.dto.documentdto;

import com.example.testproject1.model.document.TaskDocument;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для сохранения List документов в базу данных
 *
 * @author smigranov
 */
public class TaskDocumentDtoList {
    /**
     * List TaskDocument
     */
    private final List<TaskDocumentDTO> taskDocumentList = new ArrayList<>();

    /**
     * Метод получения списка IncomingDocument
     *
     * @return {@link List} объектов {@link TaskDocument}
     */
    @JsonProperty("list")
    public List<TaskDocumentDTO> getTaskDocumentList() {
        return taskDocumentList;
    }
}
