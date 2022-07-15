package com.example.testproject1.model;


/**
 * Базовый абстрактный класс документов
 *
 * @author smigranov
 * @version 1.0
 * Базовый абстрактный класс BaseDocument для {@link TaskDocument} ,{@link IncomingDocument} ,{@link OutgoingDocument}
 */


public class BaseDocument {
    /**
     * идентификатор документа вычисляемое
     */
    public static Long identifikator = 1l;
    /**
     * идентификатор документа
     */
    public Long id;
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

    public BaseDocument(Long id, String documentName, String documentText, Long documentRegNumber, String documentData, String documentAuthor) {
        this.id = id;
        this.documentName = documentName;
        this.documentText = documentText;
        this.documentRegNumber = documentRegNumber;
        this.documentData = documentData;
        this.documentAuthor = documentAuthor;
    }

    public BaseDocument() {
    }

    public static Long getIdentifikator() {
        return identifikator;
    }

    public static void setIdentifikator(Long identifikator) {
        BaseDocument.identifikator = identifikator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentText() {
        return documentText;
    }

    public void setDocumentText(String documentText) {
        this.documentText = documentText;
    }

    public Long getDocumentRegNumber() {
        return documentRegNumber;
    }

    public void setDocumentRegNumber(Long documentRegNumber) {
        this.documentRegNumber = documentRegNumber;
    }

    public String getDocumentData() {
        return documentData;
    }

    public void setDocumentData(String documentData) {
        this.documentData = documentData;
    }

    public String getDocumentAuthor() {
        return documentAuthor;
    }

    public void setDocumentAuthor(String documentAuthor) {
        this.documentAuthor = documentAuthor;
    }

    @Override
    public String toString() {
        return "BaseDocument{" +
                "id=" + id +
                ", documentName='" + documentName + '\'' +
                ", documentText='" + documentText + '\'' +
                ", documentRegNumber=" + documentRegNumber +
                ", documentData='" + documentData + '\'' +
                ", documentAuthor='" + documentAuthor + '\'' +
                '}';
    }
}






