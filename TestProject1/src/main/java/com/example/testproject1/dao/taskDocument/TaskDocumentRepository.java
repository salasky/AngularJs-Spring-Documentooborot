package com.example.testproject1.dao.taskDocument;

import com.example.testproject1.model.document.TaskDocument;

import java.util.List;
import java.util.Optional;

public interface TaskDocumentRepository {
    Integer create(TaskDocument taskDocument);

    List<TaskDocument> getAll();

    Optional<TaskDocument> getById(String id);

    Integer update(TaskDocument taskDocument);

    Integer deleteAll();

    Integer deleteById(String id);
}
