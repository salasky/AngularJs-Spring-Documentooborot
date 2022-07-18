package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.BaseDocument;
import org.springframework.stereotype.Service;

/**
 * Абстрактный класс фабрики для {@link TaskDocumentFactory},{@link IncomingDocumentFactory},{@link OutgoingDocumentFactory}
 *
 * @author smigranov
 */
@Service
public abstract class DocumentFactory {
    /**
     *
     * @return Возвращает объект,класс которого наследуется от {@link BaseDocument}
     *
     */
    public abstract BaseDocument createDocument();
}
