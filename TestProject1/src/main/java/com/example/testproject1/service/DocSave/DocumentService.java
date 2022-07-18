package com.example.testproject1.service.DocSave;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;

public interface DocumentService {

    void documentAdd(BaseDocument baseDocument) throws DocumentExistsException;

    void generateDocument(String task, String incoming, String outgoing);

    public void genereteReport();

}
