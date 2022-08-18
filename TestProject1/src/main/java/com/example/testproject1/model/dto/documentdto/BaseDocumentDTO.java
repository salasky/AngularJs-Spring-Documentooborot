package com.example.testproject1.model.dto.documentdto;

import com.example.testproject1.model.document.BaseDocument;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * DTO класс для сущности {@link BaseDocument}
 *
 * @author smigranov
 */
public class BaseDocumentDTO {
    /**
     * идентификатор документа
     */
    protected UUID id;
    /**
     * название документа
     */
    @NotNull(message = "name не может быть null")
    protected String name;
    /**
     * Tекст документа
     */
    @NotNull(message = "text не может быть null")
    protected String text;
    /**
     * Регистрационный номер документа
     */
    @NotNull(message = "regNumber не может быть null")
    protected Long regNumber;
    /**
     * дата регистрации документа
     */
    protected Timestamp creatingDate;
    /**
     * автор id документа
     */
    @NotNull(message = "authorId не может быть null")
    protected UUID authorId;

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

    public void setCreatingDate(Timestamp creatingDate) {
        this.creatingDate = creatingDate;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }
}
