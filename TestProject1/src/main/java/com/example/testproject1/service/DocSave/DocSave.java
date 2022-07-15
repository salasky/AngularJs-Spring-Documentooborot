package com.example.testproject1.service.DocSave;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.BaseDocument;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс сохранения объектов созданных с помощью Builder-ов
 * @author smigranov
 * @version 1.0
 */
@Service
public class DocSave {
    /**
     * Лист для сохранения объектов унаследованных от {@link BaseDocument}
     */
    public static List<BaseDocument> documentList = new ArrayList<>();

    /**
     * @param baseDocument передаем объект наследник от {@link BaseDocument} для сохранения в {@link DocSave#documentList}
     */
    public void docSave(BaseDocument baseDocument) throws DocumentExistsException {

        for (BaseDocument bd:documentList
             ) {
            if(bd.getId()==baseDocument.getId() || bd.getDocumentRegNumber()==baseDocument.getDocumentRegNumber()){
                throw new DocumentExistsException(bd.getDocumentRegNumber(),"Document number "+bd.getDocumentRegNumber()+" exist");
            }

        }

        documentList.add(baseDocument);
    }

}
