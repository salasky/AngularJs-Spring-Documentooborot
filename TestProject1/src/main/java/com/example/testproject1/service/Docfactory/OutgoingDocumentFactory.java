package com.example.testproject1.service.Docfactory;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.OutgoingDocument;
import org.springframework.stereotype.Service;

@Service
public class OutgoingDocumentFactory extends DocFactory{
    @Override
    public BaseDocument createDocument() {
        return new OutgoingDocument();
    }
}
