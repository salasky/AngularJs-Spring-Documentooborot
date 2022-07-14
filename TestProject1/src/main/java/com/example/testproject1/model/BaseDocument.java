package com.example.testproject1.model;

import lombok.*;


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






