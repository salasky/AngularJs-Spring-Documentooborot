package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.document.OutgoingDocument;
import org.springframework.stereotype.Service;

/**
 * Класс фабрики для {@link OutgoingDocument}
 * Для установки полей характерных только Исходящим документам.
 *
 * @author smigranov
 */
@Service
public class OutgoingDocumentFactory extends DocumentFactory<OutgoingDocument.OutgoingBuilder> {
    /**
     * {@inheritDoc}
     */
    @Override
    public OutgoingDocument.OutgoingBuilder getBuilder() {
        return OutgoingDocument.newBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OutgoingDocument.OutgoingBuilder setFields(OutgoingDocument.OutgoingBuilder builder) {
        return builder.setDocSender(randomizer.getRandOutgoingDocumentSender())
                .setDocDeliveryType(randomizer.getRandOutgoingDocumentDeliveryType());
    }
}


