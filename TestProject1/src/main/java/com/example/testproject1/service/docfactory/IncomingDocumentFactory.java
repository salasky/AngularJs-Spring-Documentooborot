package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.service.randomizer.Randomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс фабрики для {@link com.example.testproject1.model.IncomingDocument}
 * @author smigranov
 * @version 1.0
 */
@Service
public class IncomingDocumentFactory extends DocFactory {

    /**
     * Autowired бина {@link Randomizer}
     */
    @Autowired
    private Randomizer randomizer;

    @Override
    public BaseDocument createDocument() {
        return IncomingDocument.newBuilder().setDocId(randomizer.getRandUUID()).setDocName(randomizer.getRandDocName())
                .setDocText(randomizer.getRandDocText()).setDocRegNumber(randomizer.getRandDocumentRegNumber())
                .setDocDate(randomizer.getRandDocumentData()).setDocAuthor(randomizer.getRandDocumentAuthor())
                .setIncomingSender(randomizer.getRandIncomingDocumentSender()).setIncomingDestination(randomizer.getIncomingDocumentDestination())
                .setIncomingDocumentNumber(randomizer.getIncomingDocumentNumber()).setIncomingDocumentDate(randomizer.getRandIncomingDocumentDate())
                .build();
    }
}
