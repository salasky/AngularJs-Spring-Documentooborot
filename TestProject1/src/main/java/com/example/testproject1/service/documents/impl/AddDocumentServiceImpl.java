package com.example.testproject1.service.documents.impl;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.service.documents.AddDocumentService;
import com.example.testproject1.storage.DocumentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс реализующий интерфейс {@link AddDocumentService}
 *
 * @author smigranov
 */
@Service
public class AddDocumentServiceImpl implements AddDocumentService {
    @Autowired
    private DocumentHolder documentHolder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void documentAdd(BaseDocument baseDocument) throws DocumentExistsException {
        var documentList = documentHolder.getDocumentList();
        for (BaseDocument bd : documentList
        ) {
            if (bd.getId() == baseDocument.getId() || bd.getDocumentRegNumber() == baseDocument.getDocumentRegNumber()) {

                throw new DocumentExistsException(bd.getDocumentRegNumber(), "Document number " + bd.getDocumentRegNumber() + " exist");
            }
        }
        DocumentHolder.documentList.add(baseDocument);
    }
}
