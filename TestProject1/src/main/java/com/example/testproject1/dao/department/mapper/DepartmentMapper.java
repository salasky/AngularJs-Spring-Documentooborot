package com.example.testproject1.dao.department.mapper;

import com.example.testproject1.dao.organization.mapper.OrganizationMapper;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class DepartmentMapper implements RowMapper<Department> {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setId(UUID.fromString(rs.getString("department_id")));
        department.setFullName(rs.getString("department_full_name"));
        department.setShortName(rs.getString("department_short_name"));
        department.setSupervisor(rs.getString("department_supervisor"));
        department.setContactNumber(rs.getString("department_contact_number"));
        Organization organization = organizationMapper.mapRow(rs, rowNum);
        department.setOrganization(organization);

        return department;
    }
}
