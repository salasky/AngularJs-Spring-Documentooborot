package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.model.TaskDocument;
import org.springframework.stereotype.Service;

/**
 * Класс фабрики для {@link com.example.testproject1.model.IncomingDocument}
 *
 * @author smigranov
 */
@Service
public class IncomingDocumentFactory extends DocumentFactory<IncomingDocument.IncomingDocumentBuilder> {
    @Override
    public IncomingDocument.IncomingDocumentBuilder getBuilder() {
        return IncomingDocument.newBuilder();
    }

    @Override
    public IncomingDocument.IncomingDocumentBuilder setFields(IncomingDocument.IncomingDocumentBuilder builder) {
        return builder.setIncomingSender(randomizer.getRandIncomingDocumentSender())
                .setIncomingDestination(randomizer.getIncomingDocumentDestination())
                .setIncomingDocumentNumber(randomizer.getIncomingDocumentNumber())
                .setIncomingDocumentDate(randomizer.getRandIncomingDocumentDate());
    }
}


