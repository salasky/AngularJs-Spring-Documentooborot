package com.example.testproject1.service.docfactory;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.service.docBuilder.IncomingBuilder;
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
    Logger logger = LoggerFactory.getLogger(IncomingDocumentFactory.class);
    /**
     * Автоваирим объект {@link com.example.testproject1.service.docBuilder.IncomingBuilderImpl}
     */
    @Autowired
    private IncomingBuilder incomingBuilder;

    @Override
    public BaseDocument createDocument() {

        try {
            return incomingBuilder.fixDocumentName().fixDocumentText().fixDocumentRegNumber().fixDocumentData().fixDocumentAuthor()
                    .fixIncomingDocumentSender().fixIncomingDocumentDestination().fixIncomingDocumentNumber().fixIncomingDocumentDate().build();
        } catch (DocumentExistsException e) {
            /*      throw new RuntimeException(e);//Ломать или продолжить*/
            logger.error(e.getMessage());
            return null;
        }
    }
}
