package com.example.testproject1.service.docfactory;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import org.springframework.stereotype.Service;

/**
 * Абстрактный класс фабрики для {@link TaskDocumentFactory},{@link IncomingDocumentFactory},{@link OutgoingDocumentFactory}
 * @author smigranov
 * @version 1.0
 */
@Service
public abstract class DocFactory {
    /**
     *
     * @return Возвращает объект,класс которого наследуется от {@link BaseDocument}
     *
     */
    public abstract BaseDocument createDocument();
}
