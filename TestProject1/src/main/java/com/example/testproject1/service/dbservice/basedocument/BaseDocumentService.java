package com.example.testproject1.service.dbservice.basedocument;

import com.example.testproject1.model.document.BaseDocument;

import java.util.List;
import java.util.Optional;

public interface BaseDocumentService {
    Optional<BaseDocument> create(BaseDocument baseDocument);

    List<BaseDocument> getall();

    Optional<BaseDocument> getById(String id);

    Optional<BaseDocument> update(BaseDocument baseDocument);

    void deleteAll();

    void deleteById(String id);

    boolean existByRegNumber(Long regNumber);
}
