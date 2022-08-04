package com.example.testproject1.dao.baseDocument.mapper;

import com.example.testproject1.dao.person.mapper.PersonMapper;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Маппер для класса {@link BaseDocument}
 *
 * @author smigranov
 */
@Component
public class BaseDocumentMapper implements RowMapper<BaseDocument> {
    /**
     * Бин маппер {@link Person}
     */
    @Autowired
    private PersonMapper personMapper;
    @Override
    public BaseDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        BaseDocument baseDocument=new BaseDocument();
        baseDocument.setId(UUID.fromString(rs.getString("base_document_id")));
        baseDocument.setName(rs.getString("base_document_name"));
        baseDocument.setText(rs.getString("base_document_text"));
        baseDocument.setRegNumber(rs.getLong("base_document_number"));
        baseDocument.setCreatingDate(rs.getTimestamp("base_document_date"));

        Person person=personMapper.mapRow(rs,rowNum);
        baseDocument.setAuthor(person);
        return baseDocument;
    }
}
