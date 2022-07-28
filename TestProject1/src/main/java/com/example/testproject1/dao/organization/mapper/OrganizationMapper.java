package com.example.testproject1.dao.organization.mapper;

import com.example.testproject1.model.staff.Organization;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
@Component
public class OrganizationMapper implements RowMapper<Organization> {
    @Override
    public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
        Organization organization=new Organization();
        organization.setId(UUID.fromString(rs.getString("id")));
        organization.setFullName(rs.getString("fullName"));
        organization.setShortName(rs.getString("shortName"));
        organization.setSupervisor(rs.getString("supervisor"));
        organization.setContactNumber(rs.getString("contactNumber"));
        return organization;
    }
}
