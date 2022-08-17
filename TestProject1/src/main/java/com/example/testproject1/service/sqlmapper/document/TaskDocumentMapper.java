package com.example.testproject1.service.sqlmapper.document;

import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.staff.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Маппер для класса {@link TaskDocument}
 *
 * @author smigranov
 */
@Component
public class TaskDocumentMapper implements RowMapper<TaskDocument> {

    @Autowired
    private BaseDocumentMapper baseDocumentMapper;
    /**
     * Название столбца для мапинга в поле task_document_out_date
     */
    private final String TASK_DOCUMENT_OUT_DATE = "task_document_out_date";
    /**
     * Название столбца для мапинга в поле task_document_exec_period
     */
    private final String TASK_DOCUMENT_EXEC_PERIOD = "task_document_exec_period";
    /**
     * Название столбца для мапинга в поле task_document_sign_of_control
     */
    private final String TASK_DOCUMENT_SIGN_OF_CONTROL = "task_document_sign_of_control";
    /**
     * Название столбца для мапинга в поле person_response_id
     */
    private final String PERSON_RESPONSE_ID = "person_response_id";
    /**
     * Название столбца для мапинга в поле person_control_id
     */
    private final String PERSON_CONTROL_ID = "person_control_id";


    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Мапим baseDocument к taskDocument
        BaseDocument baseDocument = baseDocumentMapper.mapRow(rs, rowNum);

        //Мапим Person(responsible) к taskDocument
        Person response = new Person();
        response.setId(UUID.fromString(rs.getString(PERSON_RESPONSE_ID)));

        //Мапим Person(control_person) к taskDocument
        Person controlPerson = new Person();
        controlPerson.setId(UUID.fromString(rs.getString(PERSON_CONTROL_ID)));

        return (TaskDocument) TaskDocument.newBuilder()
                .setTaskDate(rs.getTimestamp(TASK_DOCUMENT_OUT_DATE))
                .setTaskExecPeriod(rs.getString(TASK_DOCUMENT_EXEC_PERIOD))
                .setTaskSignOfControl(rs.getBoolean(TASK_DOCUMENT_SIGN_OF_CONTROL))
                .setTaskResponsPerson(response)
                .setTaskControlPerson(controlPerson)
                .setDocId(baseDocument.getId())
                .setDocName(baseDocument.getName())
                .setDocText(baseDocument.getText())
                .setDocAuthor(baseDocument.getAuthor())
                .setDocRegNumber(baseDocument.getRegNumber())
                .setDocDate(baseDocument.getCreatingDate()).build();
    }
}
