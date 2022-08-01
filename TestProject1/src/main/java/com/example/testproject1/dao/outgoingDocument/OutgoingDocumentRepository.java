package com.example.testproject1.dao.outgoingDocument;

import com.example.testproject1.model.document.OutgoingDocument;

import java.util.List;
import java.util.Optional;

public interface OutgoingDocumentRepository {
    Integer create(OutgoingDocument outgoingDocument);

    List<OutgoingDocument> getAll();

    Optional<OutgoingDocument> getById(String id);

    Integer update(OutgoingDocument outgoingDocument);

    Integer deleteAll();

    Integer deleteById(String id);
}
