package com.example.testproject1.service.docfactory;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.service.docBuilder.IncomingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomingDocumentFactory extends DocFactory{

    @Autowired
    private IncomingBuilder incomingBuilder;
    @Override
    public BaseDocument createDocument() {


        try {
            return incomingBuilder.fixDocumentName().fixDocumentText().fixDocumentRegNumber().fixDocumentData().fixDocumentAuthor()
                    .fixIncomingDocumentSender().fixIncomingDocumentDestination().fixIncomingDocumentNumber().fixIncomingDocumentDate().build();
        } catch (DocumentExistsException e) {
      /*      throw new RuntimeException(e);//Ломать или продолжить*/
            System.out.println(e.getMessage());
            return null;
        }
    }
}
