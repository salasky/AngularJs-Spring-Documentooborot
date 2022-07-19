package com.example.testproject1.model;


import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Базовый класс BaseDocument для {@link TaskDocument} ,{@link IncomingDocument} ,{@link OutgoingDocument}
 *
 * @author smigranov
 */
public class BaseDocument implements Comparable<BaseDocument> {
    /**
     * идентификатор документа
     */
    protected UUID id;
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
    protected Date documentDate;
    /**
     * автор документа
     */
    protected String documentAuthor;

    public BaseDocument() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Date getDocumentData() {
        return documentDate;
    }

    public void setDocumentData(Date documentData) {
        this.documentDate = documentData;
    }

    public String getDocumentAuthor() {
        return documentAuthor;
    }

    public void setDocumentAuthor(String documentAuthor) {
        this.documentAuthor = documentAuthor;
    }

    /**
     * {@inheritDoc}
     *
     * @param o Объект для сравнивания
     * @return Метод equals для класса BaseDocument
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDocument that = (BaseDocument) o;
        return Objects.equals(id, that.id) && Objects.equals(documentName, that.documentName) && Objects.equals(documentText, that.documentText) && Objects.equals(documentRegNumber, that.documentRegNumber) && Objects.equals(documentDate, that.documentDate) && Objects.equals(documentAuthor, that.documentAuthor);
    }
    /**
     * {@inheritDoc}
     *
     * @return hasCode BaseDocument
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, documentName, documentText, documentRegNumber, documentDate, documentAuthor);
    }
    /**
     * {@inheritDoc}
     *
     * @param o the object to be compared.
     * @return Возвращает целое число, положительное число-текущий объект больше передваемого,0-объекты равны
     */
    @Override
    public int compareTo(BaseDocument o) {
        return Comparator.comparing(BaseDocument::getDocumentData).thenComparing(BaseDocument::getDocumentRegNumber).compare(this, o);
    }

    /**
     * @return возвращает объект builder
     */
    public static BaseDocument.BaseDocumentBuilder newBuilder() {
        return new BaseDocument().new BaseDocumentBuilder();
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class BaseDocumentBuilder {
        public BaseDocumentBuilder() {
        }

        public BaseDocumentBuilder setDocId(UUID Id) {
            BaseDocument.this.id = Id;
            return this;
        }

        public BaseDocumentBuilder setDocName(String docName) {
            BaseDocument.this.documentName = docName;
            return this;
        }

        public BaseDocumentBuilder setDocText(String docText) {
            BaseDocument.this.documentText = docText;
            return this;
        }

        public BaseDocumentBuilder setDocRegNumber(Long docRegNumber) {
            BaseDocument.this.documentRegNumber = docRegNumber;
            return this;
        }

        public BaseDocumentBuilder setDocDate(Date docDate) {
            BaseDocument.this.documentDate = docDate;
            return this;
        }

        public BaseDocumentBuilder setDocAuthor(String docAuthor) {
            BaseDocument.this.documentAuthor = docAuthor;
            return this;
        }

        public BaseDocument build() {
            return BaseDocument.this;
        }
    }
}






