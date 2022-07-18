package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.service.randomizer.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Абстрактный класс фабрики для {@link TaskDocumentFactory},{@link IncomingDocumentFactory},{@link OutgoingDocumentFactory}
 *
 * @author smigranov
 */
@Service
public abstract class DocumentFactory {
    /**
     * Autowired бина {@link Randomizer}
     */
    @Autowired
    protected Randomizer randomizer;
    /**
     *
     * @return Возвращает объект,класс которого наследуется от {@link BaseDocument}
     *
     */
    public abstract BaseDocument createDocument();
}
