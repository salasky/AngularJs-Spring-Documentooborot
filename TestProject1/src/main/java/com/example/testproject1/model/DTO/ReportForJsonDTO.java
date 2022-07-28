package com.example.testproject1.model.DTO;

import com.example.testproject1.model.documents.BaseDocument;
import com.example.testproject1.model.staff.Person;

import java.util.List;

/**
 * Класс для десериализации документов в json
 *
 * @author smigranov
 */
public class ReportForJsonDTO {
    /**
     * Автор документа
     */
    private Person person;
    /**
     * Список документов
     */
    private List<BaseDocument> documentList;

    public ReportForJsonDTO() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<BaseDocument> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<BaseDocument> documentList) {
        this.documentList = documentList;
    }

    public static ReportBuilder newBuilder() {
        return new ReportForJsonDTO().new ReportBuilder();
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class ReportBuilder {
        private ReportBuilder() {
            // private constructor
        }

        public ReportBuilder setPerson(Person person) {
            ReportForJsonDTO.this.person = person;
            return this;
        }

        public ReportBuilder setDocumentList(List<BaseDocument> baseDocumentsList) {
            ReportForJsonDTO.this.documentList = baseDocumentsList;
            return this;
        }

        /**
         * Метод build
         *
         * @return Возвращает объект класса {@link ReportForJsonDTO}
         */
        public ReportForJsonDTO build() {
            return ReportForJsonDTO.this;
        }
    }
}


