package com.example.testproject1.dao.department.mapper;

import com.example.testproject1.dao.organization.mapper.OrganizationMapper;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
/**
 * Маппер для класса {@link Department}
 *
 * @author smigranov
 */
@Component
public class DepartmentMapper implements RowMapper<Department> {
    /**
     * Бин маппер {@link Organization}
     */
    @Autowired
    private OrganizationMapper organizationMapper;
    /**
     * {@inheritDoc}
     */
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
