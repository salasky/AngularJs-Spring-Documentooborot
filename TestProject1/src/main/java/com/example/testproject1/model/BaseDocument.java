package com.example.testproject1.model;

import lombok.*;

import javax.persistence.*;
import java.util.Comparator;

/*@MappedSuperclass*/

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseDocument  {

    public static Long identifikator=1l;

    public  Long id;

    public String documentName;

    public String documentText;

    public Long documentRegNumber;

    public String documentData;

    public String documentAuthor;

}


class DocumentNumbComparator implements Comparator<BaseDocument>{
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

class DocumentDateComparator implements Comparator<BaseDocument>{

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

