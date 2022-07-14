package com.example.testproject1.model.BaseDocComporator;

import com.example.testproject1.model.BaseDocument;

import java.util.Comparator;

/**
 * Класс компоратор по дате создания документа
 */
class DocumentDateComparator implements Comparator<BaseDocument> {

    @Override
    public int compare(BaseDocument o1, BaseDocument o2) {

        String [] o1Date=o1.getDocumentData().split("-");

        String [] o2Date=o2.getDocumentData().split("-");

        if(Integer.parseInt(o1Date[0])>Integer.parseInt(o2Date[0]))
            return 1;
        else if(Integer.parseInt(o1Date[0])<Integer.parseInt(o2Date[0]))
            return -1;

        else if(Integer.parseInt(o1Date[1])>Integer.parseInt(o2Date[1]))
            return 1;
        else if(Integer.parseInt(o1Date[1])<Integer.parseInt(o2Date[1]))
            return -1;

        else if(Integer.parseInt(o1Date[2])>Integer.parseInt(o2Date[2]))
            return 1;
        else if(Integer.parseInt(o1Date[2])<Integer.parseInt(o2Date[2]))
            return -1;

        else
            return 0;

    }
}