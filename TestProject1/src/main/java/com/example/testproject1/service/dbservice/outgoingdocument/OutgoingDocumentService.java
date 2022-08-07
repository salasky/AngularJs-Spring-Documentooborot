package com.example.testproject1.service.dbservice.outgoingdocument;

import com.example.testproject1.model.document.OutgoingDocument;

import java.util.List;
import java.util.Optional;

public interface OutgoingDocumentService {
    Optional<OutgoingDocument> create(OutgoingDocument outgoingDocument);

    List<OutgoingDocument> getall();

    Optional<OutgoingDocument> getById(String id);

    Optional<OutgoingDocument> update(OutgoingDocument outgoingDocument);

    String deleteAll();

    String deleteById(String id);
}
