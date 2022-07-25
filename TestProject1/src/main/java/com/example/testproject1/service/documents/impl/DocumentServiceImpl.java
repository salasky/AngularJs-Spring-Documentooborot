package com.example.testproject1.service.documents.impl;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.service.documents.DocumentService;
import com.example.testproject1.storage.DocumentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private DocumentHolder documentHolder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(BaseDocument baseDocument) throws DocumentExistsException {
        List<BaseDocument> documentList = documentHolder.getAll();
        Optional<BaseDocument> optionalBaseDocument= documentList
                    .stream()
                    .filter(s->s.getRegNumber()==baseDocument.getRegNumber())
                    .findFirst();
            if(optionalBaseDocument.isPresent()){
                throw new DocumentExistsException(baseDocument.getRegNumber(), "Document number " + baseDocument.getRegNumber() + " exist");
        }
        documentHolder.addAll(baseDocument);
    }
}
