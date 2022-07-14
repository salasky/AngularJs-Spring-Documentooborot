package com.example.testproject1.service.DocSave;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.shell.TaskDocumentShell;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс сохранения объектов созданных с помощью Builder-ов
 */
@Service
public class DocSave {
    /**
     * Лист для сохранения объектов унаследованных от {@link BaseDocument}
     */
    public static List<BaseDocument> documentList=new ArrayList<>();

    /**
     * 
     * @param baseDocument передаем объект наследник от {@link BaseDocument} для сохранения в {@link DocSave#documentList}
     */
    public void docSave(BaseDocument baseDocument){
        documentList.add(baseDocument);
    }

}
