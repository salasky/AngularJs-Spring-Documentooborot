package com.example.testproject1.model;

import lombok.*;


/**
 * Базовый абстрактный класс документов
 * @author smigranov
 * @version 1.0
 * Базовый абстрактный класс BaseDocument для {@link TaskDocument} ,{@link IncomingDocument} ,{@link OutgoingDocument}
 *
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BaseDocument  {
    /**
     * идентификатор документа вычисляемое
     */
    public static Long identifikator=1l;
    /**
     * идентификатор документа
     */
    public  Long id;
    /**
     * название документа
     */
    public String documentName;
    /**
     * текст документа
     */
    public String documentText;
    /**
     * регистрационный номер документа
     */
    public Long documentRegNumber;
    /**
     * дата регистрации документа
     */
    public String documentData;
    /**
     * автор документа
     */
    public String documentAuthor;

}






