package com.example.testproject1.service.dbService.taskDocument;

import com.example.testproject1.model.document.TaskDocument;

import java.util.List;
import java.util.Optional;

public interface TaskDocumentService {
    Optional<TaskDocument> create(TaskDocument taskDocument);

    List<TaskDocument> getall();

    Optional<TaskDocument> getById(String id);

    Optional<TaskDocument> update(TaskDocument taskDocument);

    String deleteAll();

    String deleteById(String id);
}
