package com.example.testproject1.model;

import com.example.testproject1.model.person.Person;

import java.util.List;

/**
 * Класс дял десериализации документов в json
 *
 * @author smigranov
 */
public class ReportForJson {
    /**
     * Автор документа
     */
    private Person person;
    /**
     * Список документов
     */
    private List<BaseDocument> documentList;

    public ReportForJson() {
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
    public static ReporBuilder newBuilder() {
        return new ReportForJson().new ReporBuilder();
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class ReporBuilder {
        private ReporBuilder() {
            // private constructor
        }

        public ReporBuilder setPerson(Person person) {
            ReportForJson.this.person = person;
            return this;
        }

        public ReporBuilder setDocumentList(List<BaseDocument> baseDocumentsList) {
            ReportForJson.this.documentList = baseDocumentsList;
            return this;
        }

        public ReportForJson build() {
            return ReportForJson.this;
        }
    }
}


