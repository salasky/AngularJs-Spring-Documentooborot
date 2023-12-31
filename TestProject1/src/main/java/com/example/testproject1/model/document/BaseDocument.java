package com.example.testproject1.model.document;


import com.example.testproject1.model.staff.Person;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Comparator;
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
    @Column(name = "id")
    protected UUID id;
    /**
     * название документа
     */
    @Column(name = "name")
    protected String name;
    /**
     * Tекст документа
     */
    @Column(name = "text")
    protected String text;
    /**
     * Регистрационный номер документа
     */
    @Column(name = "reg_number")
    protected Long regNumber;
    /**
     * дата регистрации документа
     */
    @Column(name = "creating_date")
    protected Timestamp creatingDate;
    /**
     * автор документа
     */
    @Column(name = "author")
    protected Person author;

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

    public Timestamp getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Timestamp documentData) {
        this.creatingDate = documentData;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person documentAuthor) {
        this.author = documentAuthor;
    }

    /**
     * {@inheritDoc}
     *
     * @param o Объект для сравнивания
     * @return объект класса boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseDocument)) return false;
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
        return Comparator.comparing(BaseDocument::getCreatingDate).thenComparing(BaseDocument::getRegNumber).compare(this, o);
    }

    /**
     * {@inheritDoc}
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
     * Статический метод для создания builder для {@link BaseDocument}
     *
     * @return возвращает объект builder для {@link BaseDocument}
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

        public BaseDocumentBuilder setDocDate(Timestamp docDate) {
            BaseDocument.this.creatingDate = docDate;
            return this;
        }

        public BaseDocumentBuilder setDocAuthor(Person docAuthor) {
            BaseDocument.this.author = docAuthor;
            return this;
        }

        /**
         * Метод build
         *
         * @return возвращает объект класса {@link BaseDocument}
         */
        public BaseDocument build() {
            return BaseDocument.this;
        }
    }
}






