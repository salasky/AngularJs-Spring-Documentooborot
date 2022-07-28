package com.example.testproject1.dao.department.mapper;

import com.example.testproject1.dao.organization.OrganizationRepository;
import com.example.testproject1.dao.organization.mapper.OrganizationMapper;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;
@Component
public class DepartmentMapper implements RowMapper<Department> {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department=new Department();
        department.setId(UUID.fromString(rs.getString("department_id")));
        department.setFullName(rs.getString("department_fullName"));
        department.setShortName(rs.getString("department_shortName"));
        department.setSupervisor(rs.getString("department_supervisor"));
        department.setContactNumber(rs.getString("department_contactNumber"));
        if(!(department ==null)) {
            Organization organization = new Organization();
            organization.setId(UUID.fromString(rs.getString("organization_id")));
            organization.setFullName(rs.getString("organization_fullName"));
            organization.setShortName(rs.getString("organization_shortName"));
            organization.setSupervisor(rs.getString("organization_supervisor"));
            organization.setContactNumber(rs.getString("organization_contactNumber"));
            department.setOrganization(organization);
        }
        return department;
    }
}
