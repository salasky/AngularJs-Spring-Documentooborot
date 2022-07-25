package com.example.testproject1.model;


import com.example.testproject1.service.visitorPatternRelase.DocumentInspector;
import com.example.testproject1.service.visitorPatternRelase.DocumentVisitor;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Базовый класс BaseDocument для {@link TaskDocument} ,{@link IncomingDocument} ,{@link OutgoingDocument}
 *
 * @author smigranov
 */
public class BaseDocument implements Comparable<BaseDocument>, DocumentVisitor {
    /**
     * идентификатор документа
     */
    protected UUID id;
    /**
     * название документа
     */
    protected String name;
    /**
     * Tекст документа
     */
    protected String text;
    /**
     * Регистрационный номер документа
     */
    protected Long regNumber;
    /**
     * дата регистрации документа
     */
    protected Date creatingDate;
    /**
     * автор документа
     */
    protected String author;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(Long regNumber) {
        this.regNumber = regNumber;
    }

    public Date getDocumentData() {
        return creatingDate;
    }

    public void setDocumentData(Date documentData) {
        this.creatingDate = documentData;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(text, that.text) && Objects.equals(regNumber, that.regNumber) && Objects.equals(creatingDate, that.creatingDate) && Objects.equals(author, that.author);
    }

    /**
     * {@inheritDoc}
     *
     * @return hasCode BaseDocument
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, regNumber, creatingDate, author);
    }

    /**
     * {@inheritDoc}
     *
     * @param o the object to be compared.
     * @return Возвращает целое число, положительное число-текущий объект больше передваемого,0-объекты равны
     */
    @Override
    public int compareTo(BaseDocument o) {
        return Comparator.comparing(BaseDocument::getDocumentData).thenComparing(BaseDocument::getRegNumber).compare(this, o);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public String toString() {
        Object[] taskArgs = {id, name, text, regNumber, creatingDate, author};
        MessageFormat form = new MessageFormat(
                "id= {0} documentName= {1}, documentText= {2}, documentRegNumber= {3}" +
                        ", documentData= {4}, documentAuthor= {5}");
        return form.format(taskArgs);
    }

    /**
     * @return возвращает объект builder
     */
    public static BaseDocument.BaseDocumentBuilder newBuilder() {
        return new BaseDocument().new BaseDocumentBuilder();
    }

    /**
     * {@inheritDoc}
     *
     * @param documentInspector
     * @return
     */
    @Override
    public String accept(DocumentInspector documentInspector) {
        return documentInspector.visit(this);
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
            BaseDocument.this.name = docName;
            return this;
        }

        public BaseDocumentBuilder setDocText(String docText) {
            BaseDocument.this.text = docText;
            return this;
        }

        public BaseDocumentBuilder setDocRegNumber(Long docRegNumber) {
            BaseDocument.this.regNumber = docRegNumber;
            return this;
        }

        public BaseDocumentBuilder setDocDate(Date docDate) {
            BaseDocument.this.creatingDate = docDate;
            return this;
        }

        public BaseDocumentBuilder setDocAuthor(String docAuthor) {
            BaseDocument.this.author = docAuthor;
            return this;
        }

        public BaseDocument build() {
            return BaseDocument.this;
        }
    }
}






