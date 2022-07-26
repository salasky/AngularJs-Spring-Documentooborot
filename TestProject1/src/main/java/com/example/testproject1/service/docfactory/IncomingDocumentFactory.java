package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.documents.IncomingDocument;
import org.springframework.stereotype.Service;

/**
 * Класс фабрики для {@link IncomingDocument}
 *
 * @author smigranov
 */
@Service
public class IncomingDocumentFactory extends DocumentFactory<IncomingDocument.IncomingDocumentBuilder> {
    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocument.IncomingDocumentBuilder getBuilder() {
        return IncomingDocument.newBuilder();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocument.IncomingDocumentBuilder setFields(IncomingDocument.IncomingDocumentBuilder builder) {
        return builder.setIncomingSender(randomizer.getRandIncomingDocumentSender())
                .setIncomingDestination(randomizer.getIncomingDocumentDestination())
                .setIncomingDocumentNumber(randomizer.getIncomingDocumentNumber())
                .setIncomingDocumentDate(randomizer.getRandIncomingDocumentDate());
    }
}


