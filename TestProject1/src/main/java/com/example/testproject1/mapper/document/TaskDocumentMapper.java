package com.example.testproject1.mapper.document;

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
    /**
     * Бин маппер {@link BaseDocument}
     */
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
     * Название столбца для мапинга в поле person_response_first_name
     */
    private final String PERSON_RESPONSE_FIRST_NAME = "person_response_first_name";
    /**
     * Название столбца для мапинга в поле person_response_second_name
     */
    private final String PERSON_RESPONSE_SECOND_NAME = "person_response_second_name";
    /**
     * Название столбца для мапинга в поле person_response_last_name
     */
    private final String PERSON_RESPONSE_LAST_NAME = "person_response_last_name";
    /**
     * Название столбца для мапинга в поле person_control_id
     */
    private final String PERSON_CONTROL_ID = "person_control_id";
    /**
     * Название столбца для мапинга в поле person_control_first_name
     */
    private final String PERSON_CONTROL_FIRST_NAME = "person_control_first_name";
    /**
     * Название столбца для мапинга в поле person_control_second_name
     */
    private final String PERSON_CONTROL_SECOND_NAME = "person_control_second_name";
    /**
     * Название столбца для мапинга в поле person_control_last_name
     */
    private final String PERSON_CONTROL_LAST_NAME = "person_control_last_name";

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Мапим baseDocument к taskDocument
        BaseDocument baseDocument = baseDocumentMapper.mapRow(rs, rowNum);
        TaskDocument taskDocument = new TaskDocument();
        taskDocument.setOutDate(rs.getTimestamp(TASK_DOCUMENT_OUT_DATE));
        taskDocument.setExecPeriod(rs.getString(TASK_DOCUMENT_EXEC_PERIOD));
        taskDocument.setSignOfControl(rs.getBoolean(TASK_DOCUMENT_SIGN_OF_CONTROL));
        taskDocument.setId(baseDocument.getId());
        taskDocument.setName(baseDocument.getName());
        taskDocument.setText(baseDocument.getText());
        taskDocument.setAuthor(baseDocument.getAuthor());
        taskDocument.setRegNumber(baseDocument.getRegNumber());
        taskDocument.setCreatingDate(baseDocument.getCreatingDate());

        //Мапим Person(responsible) к taskDocument
        Person response = new Person();
        response.setId(UUID.fromString(rs.getString(PERSON_RESPONSE_ID)));
        response.setFirstName(rs.getString(PERSON_RESPONSE_FIRST_NAME));
        response.setSecondName(rs.getString(PERSON_RESPONSE_SECOND_NAME));
        response.setLastName(rs.getString(PERSON_RESPONSE_LAST_NAME));
        taskDocument.setResponsible(response);

        //Мапим Person(control_person) к taskDocument
        Person controlPerson = new Person();
        controlPerson.setId(UUID.fromString(rs.getString(PERSON_CONTROL_ID)));
        controlPerson.setFirstName(rs.getString(PERSON_CONTROL_FIRST_NAME));
        controlPerson.setSecondName(rs.getString(PERSON_CONTROL_SECOND_NAME));
        controlPerson.setLastName(rs.getString(PERSON_CONTROL_LAST_NAME));

        taskDocument.setControlPerson(controlPerson);
        return taskDocument;
    }
}
