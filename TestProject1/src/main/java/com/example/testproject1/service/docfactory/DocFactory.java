package com.example.testproject1.service.docfactory;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import org.springframework.stereotype.Service;

@Service
public abstract class DocFactory {
    public abstract BaseDocument createDocument() throws DocumentExistsException;
}
