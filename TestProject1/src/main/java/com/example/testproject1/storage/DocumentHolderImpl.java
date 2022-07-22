package com.example.testproject1.storage;

import com.example.testproject1.model.BaseDocument;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс сохранения объектов BaseDocument созданных с помощью Builder-ов
 *
 * @author smigranov
 */
@Service
public class DocumentHolderImpl implements DocumentHolder {
    /**
     * Лист для сохранения объектов унаследованных от {@link BaseDocument}
     */
    private List<BaseDocument> documentList = new ArrayList<>();
    /**
     * {@inheritDoc}
     */
    @Override
    public List<BaseDocument> getAll() {
        return documentList;
    }

    @Override
    public void addAll(BaseDocument baseDocumentList) {
        documentList.add(baseDocumentList);
    }
}
