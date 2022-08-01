package com.example.testproject1.dao.incomingDocumrnt;

import com.example.testproject1.model.document.IncomingDocument;

import java.util.List;
import java.util.Optional;

public interface IncomingDocumentRepository {
    Integer create(IncomingDocument incomingDocument);

    List<IncomingDocument> getAll();

    Optional<IncomingDocument> getById(String id);

    Integer update(IncomingDocument incomingDocument);

    Integer deleteAll();

    Integer deleteById(String id);
}
