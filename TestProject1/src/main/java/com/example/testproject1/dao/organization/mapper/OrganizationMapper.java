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
    /**
     * {@inheritDoc}
     */
    @Override
    public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
        Organization organization = new Organization();
        organization.setId(UUID.fromString(rs.getString("organization_id")));
        organization.setFullName(rs.getString("organization_full_name"));
        organization.setShortName(rs.getString("organization_short_name"));
        organization.setSupervisor(rs.getString("organization_supervisor"));
        organization.setContactNumber(rs.getString("organization_contact_number"));
        return organization;
    }
}
