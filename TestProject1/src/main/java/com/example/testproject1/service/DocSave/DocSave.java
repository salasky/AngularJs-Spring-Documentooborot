package com.example.testproject1.service.DocSave;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.shell.TaskDocumentShell;
import org.springframework.stereotype.Service;

@Service
public class DocSave {
    public void docSave(BaseDocument baseDocument){
        TaskDocumentShell.documentList.add(baseDocument);
    }

}
