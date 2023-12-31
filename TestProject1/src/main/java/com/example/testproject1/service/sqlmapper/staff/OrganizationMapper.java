package com.example.testproject1.service.sqlmapper.staff;

import com.example.testproject1.model.staff.Organization;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Маппер для класса {@link Organization}
 *
 * @author smigranov
 */
@Component
public class OrganizationMapper implements RowMapper<Organization> {
    /**
     * Название столбца для мапинга в поле organization_id
     */
    private final String ORGANIZATION_ID = "organization_id";
    /**
     * Название столбца для мапинга в поле organization_full_name
     */
    private final String ORGANIZATION_FULL_NAME = "organization_full_name";
    /**
     * Название столбца для мапинга в поле organization_short_name
     */
    private final String ORGANIZATION_SHORT_NAME = "organization_short_name";
    /**
     * Название столбца для мапинга в поле organization_supervisor
     */
    private final String ORGANIZATION_SUPERVISOR = "organization_supervisor";
    /**
     * Название столбца для мапинга в поле organization_contact_number
     */
    private final String ORGANIZATION_CONTACT_NUMBER = "organization_contact_number";

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Organization.newBuilder()
                .setId(UUID.fromString(rs.getString(ORGANIZATION_ID)))
                .setFullName(rs.getString(ORGANIZATION_FULL_NAME))
                .setShortName(rs.getString(ORGANIZATION_SHORT_NAME))
                .setSupervisor(rs.getString(ORGANIZATION_SUPERVISOR))
                .setContactNumber(List.of(rs.getString(ORGANIZATION_CONTACT_NUMBER))).build();
    }
}
