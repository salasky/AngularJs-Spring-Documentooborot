package com.example.testproject1.model;


import java.util.Objects;

/**
 * Базовый абстрактный класс документов
 *
 * @author smigranov
 * @version 1.0
 * Базовый абстрактный класс BaseDocument для {@link TaskDocument} ,{@link IncomingDocument} ,{@link OutgoingDocument}
 */


public abstract class BaseDocument {
    /**
     * идентификатор документа вычисляемое
     */
    protected static Long identifikator = 1l;
    /**
     * идентификатор документа
     */
    protected Long id;
    /**
     * название документа
     */
    protected String documentName;
    /**
     * текст документа
     */
    protected String documentText;
    /**
     * регистрационный номер документа
     */
    protected Long documentRegNumber;
    /**
     * дата регистрации документа
     */
    protected String documentDate;
    /**
     * автор документа
     */
    protected String documentAuthor;

    public BaseDocument(Long id, String documentName, String documentText, Long documentRegNumber, String documentData, String documentAuthor) {
        this.id = id;
        this.documentName = documentName;
        this.documentText = documentText;
        this.documentRegNumber = documentRegNumber;
        this.documentDate = documentData;
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
        return documentDate;
    }

    public void setDocumentData(String documentData) {
        this.documentDate = documentData;
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
                ", documentData='" + documentDate + '\'' +
                ", documentAuthor='" + documentAuthor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDocument that = (BaseDocument) o;
        return Objects.equals(id, that.id) && Objects.equals(documentName, that.documentName) && Objects.equals(documentText, that.documentText) && Objects.equals(documentRegNumber, that.documentRegNumber) && Objects.equals(documentDate, that.documentDate) && Objects.equals(documentAuthor, that.documentAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentName, documentText, documentRegNumber, documentDate, documentAuthor);
    }
}






