package com.example.testproject1.service.Docfactory;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import org.springframework.stereotype.Service;

@Service
public class IncomingDocumentFactory extends DocFactory{
    @Override
    public BaseDocument createDocument() {
        return new IncomingDocument();
    }
}
