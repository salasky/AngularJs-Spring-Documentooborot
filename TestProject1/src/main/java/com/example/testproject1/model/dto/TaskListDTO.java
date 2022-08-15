package com.example.testproject1.model.dto;

import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для сохранения List документов в базу данных
 *
 * @author smigranov
 */
public class TaskListDTO {
    /**
     * List TaskDocument
     */
    private final List<TaskDocument> taskDocumentList = new ArrayList<>();

    /**
     * Метод получения списка IncomingDocument
     *
     * @return {@link List} объектов {@link TaskDocument}
     */
    @JsonProperty("list")
    public List<TaskDocument> getTaskDocumentList() {
        return taskDocumentList;
    }
}
