package com.example.testproject1.service.DocSave;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.shell.TaskDocumentShell;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocSave {
    public static List<BaseDocument> documentList=new ArrayList<>();

    public void docSave(BaseDocument baseDocument){
        documentList.add(baseDocument);
    }

}
