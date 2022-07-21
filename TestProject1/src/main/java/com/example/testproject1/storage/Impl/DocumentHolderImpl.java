package com.example.testproject1.storage.Impl;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.storage.DocumentHolder;
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
    public static List<BaseDocument> documentList = new ArrayList<>();

    @Override
    public List<BaseDocument> getDocumentList() {
        return documentList;
    }
}
