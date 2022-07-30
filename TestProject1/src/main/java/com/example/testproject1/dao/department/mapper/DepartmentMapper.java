package com.example.testproject1.dao.department.mapper;

import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DepartmentMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department=new Department();
        department.setId(UUID.fromString(rs.getString("department_id")));
        department.setFullName(rs.getString("department_full_name"));
        department.setShortName(rs.getString("department_short_name"));
        department.setSupervisor(rs.getString("department_supervisor"));
        department.setContactNumber(rs.getString("department_contact_number"));
            Organization organization = new Organization();
            organization.setId(UUID.fromString(rs.getString("organization_id")));
            organization.setFullName(rs.getString("organization_full_name"));
            organization.setShortName(rs.getString("organization_short_name"));
            organization.setSupervisor(rs.getString("organization_supervisor"));
            organization.setContactNumber(rs.getString("organization_contact_number"));
        department.setOrganization(organization);

        return department;
    }
}
