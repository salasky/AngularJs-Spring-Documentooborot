package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.OutgoingDocument;
import org.springframework.stereotype.Service;

/**
 * Класс фабрики для {@link com.example.testproject1.model.OutgoingDocument}
 *
 * @author smigranov
 */
@Service
public class OutgoingDocumentFactory extends DocumentFactory<OutgoingDocument.OutgoingBuilder> {
    /**
     * {@inheritDoc}
     *
     * @return Возвращает объект класса BaseDocument
     */
    @Override
    public BaseDocument create() {
        return createBaseDocument(OutgoingDocument.newBuilder()
                .setDocSender(randomizer.getRandOutgoingDocumentSender())
                .setDocDeliveryType(randomizer.getRandOutgoingDocumentDeliveryType()));
    }
}


