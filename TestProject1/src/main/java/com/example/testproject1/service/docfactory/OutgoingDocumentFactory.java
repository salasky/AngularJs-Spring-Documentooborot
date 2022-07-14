package com.example.testproject1.service.docfactory;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.service.docBuilder.OutgoingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * класс фабрики для {@link com.example.testproject1.model.OutgoingDocument}
 */
@Service
public class OutgoingDocumentFactory extends DocFactory{
    /**
     * Автоваирим объект {@link com.example.testproject1.service.docBuilder.OutgoingBuilderImpl}
     */
    @Autowired
    private OutgoingBuilder outgoingBuilder;
    @Override
    public BaseDocument createDocument() {
        try {
            return outgoingBuilder.fixDocumentName().fixDocumentText().fixDocumentRegNumber()
                    .fixDocumentData().fixDocumentAuthor().fixOutgoingDocumentSender().fixOutgoingDocumentDeliveryType().build();
        } catch (DocumentExistsException e) {
            /*      throw new RuntimeException(e);//Ломать или продолжить*/
            System.out.println(e.getMessage());
            return null;
        }
    }
}
