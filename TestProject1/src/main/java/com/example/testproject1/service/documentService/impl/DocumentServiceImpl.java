package com.example.testproject1.service.documentService.impl;

import com.example.testproject1.exception.DocumentExistsException;
import com.example.testproject1.model.documents.BaseDocument;
import com.example.testproject1.service.documentService.DocumentService;
import com.example.testproject1.service.documentService.DocumentStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

/**
 * Класс реализующий интерфейс {@link DocumentService}
 *
 * @author smigranov
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    /**
     * Объект класса для хранения документов
     */
    @Autowired
    private DocumentStorageService documentStorageService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(BaseDocument baseDocument) throws DocumentExistsException {
        List<BaseDocument> documentList = documentStorageService.getAll();
        Optional<BaseDocument> optionalBaseDocument = documentList
                .stream()
                .filter(s -> s.getRegNumber() == baseDocument.getRegNumber())
                .findFirst();
        if (optionalBaseDocument.isPresent()) {
            throw new DocumentExistsException(baseDocument.getRegNumber(), MessageFormat.format("Document number {0}} exist", baseDocument.getRegNumber()));
        }
        documentStorageService.addAll(baseDocument);
    }
}