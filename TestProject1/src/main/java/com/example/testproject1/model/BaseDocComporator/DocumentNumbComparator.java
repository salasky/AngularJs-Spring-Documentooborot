package com.example.testproject1.model.BaseDocComporator;

import com.example.testproject1.model.BaseDocument;

import java.util.Comparator;

/**
 * Класс компоратор для сравнения по рег.номеру документов
 */
class DocumentNumbComparator implements Comparator<BaseDocument> {
    @Override
    public int compare(BaseDocument o1, BaseDocument o2) {
        if(o1.getDocumentRegNumber()>o2.getDocumentRegNumber())
            return 1;
        else if(o1.getDocumentRegNumber()< o2.getDocumentRegNumber())
            return -1;
        else
            return 0;
    }
}