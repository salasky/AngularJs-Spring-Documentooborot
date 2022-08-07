package com.example.testproject1.service.dbservice.taskdocument;

import com.example.testproject1.model.document.TaskDocument;

import java.util.List;
import java.util.Optional;

public interface TaskDocumentService {
    Optional<TaskDocument> create(TaskDocument taskDocument);

    List<TaskDocument> getall();

    Optional<TaskDocument> getById(String id);

    Optional<TaskDocument> update(TaskDocument taskDocument);

    void deleteAll();

    void deleteById(String id);
}
