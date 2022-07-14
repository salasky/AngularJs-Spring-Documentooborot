package com.example.testproject1.service.docfactory;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import org.springframework.stereotype.Service;

/**
 * Абстрактный класс фабрики для {@link TaskDocumentFactory},{@link IncomingDocumentFactory},{@link OutgoingDocumentFactory}
 */
@Service
public abstract class DocFactory {
    /**
     *
     * @return Возвращает объект,класс которого наследуется от {@link BaseDocument}
     * @throws  DocumentExistsException исключение при создании документа с существующим рег.номером
     */
    public abstract BaseDocument createDocument() throws DocumentExistsException;
}
