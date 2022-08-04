package com.example.testproject1.dao.organization.mapper;

import com.example.testproject1.model.staff.Organization;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
/**
 * Маппер для класса {@link Organization}
 *
 * @author smigranov
 */
@Component
public class OrganizationMapper implements RowMapper<Organization> {
    private final String ORGANIZATION_ID="organization_id";
    private final String ORGANIZATION_FULL_NAME="organization_full_name";
    private final String ORGANIZATION_SHORT_NAME="organization_short_name";
    private final String ORGANIZATION_SUPERVISOR="organization_supervisor";
    private final String ORGANIZATION_CONTACT_NUMBER="organization_contact_number";
    /**
     * {@inheritDoc}
     */
    @Override
    public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
        Organization organization = new Organization();
        organization.setId(UUID.fromString(rs.getString(ORGANIZATION_ID)));
        organization.setFullName(rs.getString(ORGANIZATION_FULL_NAME));
        organization.setShortName(rs.getString(ORGANIZATION_SHORT_NAME));
        organization.setSupervisor(rs.getString(ORGANIZATION_SUPERVISOR));
        organization.setContactNumber(rs.getString(ORGANIZATION_CONTACT_NUMBER));
        return organization;
    }
}
