package com.example.testproject1.service.sqlmapper.document;

import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.documentenum.DocumentDeliveryType;
import com.example.testproject1.model.staff.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Маппер для класса {@link OutgoingDocument}
 *
 * @author smigranov
 */
@Component
public class OutgoingDocumentMapper implements RowMapper<OutgoingDocument> {

    @Autowired
    private BaseDocumentMapper baseDocumentMapper;
    /**
     * Название столбца для мапинга в поле outgoing_delivery_type
     */
    private final String OUTGOING_DELIVERY_TYPE = "outgoing_delivery_type";
    /**
     * Название столбца для мапинга в поле person_sender_id
     */
    private final String PERSON_SENDER_ID = "person_sender_id";

    /**
     * {@inheritDoc}
     */
    @Override
    public OutgoingDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Мапим baseDocument к incomingDocument
        BaseDocument baseDocument = baseDocumentMapper.mapRow(rs, rowNum);

        //Мапим Person(sender) к outgoingDocument
        Person sender = new Person();
        sender.setId(UUID.fromString(rs.getString(PERSON_SENDER_ID)));

        return (OutgoingDocument) OutgoingDocument.newBuilder()
                .setDocDeliveryType(DocumentDeliveryType.valueOf(rs.getString(OUTGOING_DELIVERY_TYPE)))
                .setDocSender(sender)
                .setDocId(baseDocument.getId())
                .setDocName(baseDocument.getName())
                .setDocText(baseDocument.getText())
                .setDocAuthor(baseDocument.getAuthor())
                .setDocRegNumber(baseDocument.getRegNumber())
                .setDocDate(baseDocument.getCreatingDate()).build();
    }
}
