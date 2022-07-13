package com.example.testproject1.service.Docfactory;

import com.example.testproject1.model.BaseDocument;
import org.springframework.stereotype.Service;

@Service
public abstract class DocFactory {
    public abstract BaseDocument createDocument();
}
