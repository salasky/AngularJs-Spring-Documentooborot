package com.example.testproject1.model.DTO;

import com.example.testproject1.model.documents.BaseDocument;
import com.example.testproject1.model.staff.Person;

import java.util.List;

/**
 * Класс дял десериализации документов в json
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
    public static ReporBuilder newBuilder() {
        return new ReportForJsonDTO().new ReporBuilder();
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
            ReportForJsonDTO.this.person = person;
            return this;
        }

        public ReporBuilder setDocumentList(List<BaseDocument> baseDocumentsList) {
            ReportForJsonDTO.this.documentList = baseDocumentsList;
            return this;
        }

        public ReportForJsonDTO build() {
            return ReportForJsonDTO.this;
        }
    }
}


