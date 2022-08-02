package com.example.testproject1.dao.baseDocument;

import com.example.testproject1.model.document.BaseDocument;

import java.util.List;
import java.util.Optional;

public interface BaseDocumentRepository {
    Integer create(BaseDocument baseDocument);

    List<BaseDocument> getAll();

    Optional<BaseDocument> getById(String id);

    Integer update(BaseDocument baseDocument);

    Integer deleteAll();

    Integer deleteById(String id);

    boolean existByRegNumber(Long regNumber);

    boolean existById(String uuid);
}
