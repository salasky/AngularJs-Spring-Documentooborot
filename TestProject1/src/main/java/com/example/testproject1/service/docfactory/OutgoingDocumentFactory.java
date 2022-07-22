package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.model.OutgoingDocument;
import org.springframework.stereotype.Service;

/**
 * Класс фабрики для {@link com.example.testproject1.model.OutgoingDocument}
 *
 * @author smigranov
 */
@Service
public class OutgoingDocumentFactory extends DocumentFactory<OutgoingDocument.OutgoingBuilder> {
    @Override
    public OutgoingDocument.OutgoingBuilder getBuilder() {
        return OutgoingDocument.newBuilder();
    }
    @Override
    public OutgoingDocument.OutgoingBuilder setFields(OutgoingDocument.OutgoingBuilder builder) {
        return builder.setDocSender(randomizer.getRandOutgoingDocumentSender())
                .setDocDeliveryType(randomizer.getRandOutgoingDocumentDeliveryType());
    }
}


