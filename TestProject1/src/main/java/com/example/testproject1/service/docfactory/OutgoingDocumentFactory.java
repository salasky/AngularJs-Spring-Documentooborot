package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.OutgoingDocument;
import com.example.testproject1.service.randomizer.Randomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс фабрики для {@link com.example.testproject1.model.OutgoingDocument}
 * @author smigranov
 * @version 1.0
 */
@Service
public class OutgoingDocumentFactory extends DocFactory {
    Logger logger = LoggerFactory.getLogger(OutgoingDocumentFactory.class);
    /**
     * Autowired бина {@link Randomizer}
     */
    @Autowired
    private Randomizer randomizer;

    @Override
    public BaseDocument createDocument() {
        return OutgoingDocument.newBuilder().setDocId(randomizer.getRandUUID()).setDocName(randomizer.getRandDocName())
                .setDocText(randomizer.getRandDocText()).setDocRegNumber(randomizer.getRandDocumentRegNumber())
                .setDocDate(randomizer.getRandDocumentData()).setDocAuthor(randomizer.getRandDocumentAuthor())
                .setDocSender(randomizer.getRandOutgoingDocumentSender()).setDocDeliveryType(randomizer.getRandOutgoingDocumentDeliveryType())
                .build();
    }
}
