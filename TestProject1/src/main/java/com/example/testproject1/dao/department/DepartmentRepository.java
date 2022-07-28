package com.example.testproject1.dao.department;

import com.example.testproject1.dao.department.mapper.DepartmentMapper;
import com.example.testproject1.dao.organization.mapper.OrganizationMapper;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepository {
    /**
     * Запрос на получение всех объектов из таблицы department
     */
    private final String queryGetAll="SELECT department.id, department.fullName, department.shortName" +
            ", department.supervisor, department.contactNumber" +
            ", organization.id, organization.fullName, organization.shortName" +
            ", organization.supervisor, organization.department" +
            " FROM department INNER JOIN organization ON department.organization_id=organization.id";
    /**
     * Запрос на получение объекта по id из таблицы organization
     */
    private final String queryGetById="SELECT * FROM department WHERE id=?";
    /**
     * Запрос на создание записи в таблице organization
     */
    private final String queryCreate="INSERT INTO department VALUES (?,?,?,?,?,?)";
    /**
     * Запрос на удаление всех записей в таблице organization
     */
    private final String queryDeleteAll="DELETE FROM department";
    /**
     * Запрос на удаление записи по id в таблице organization
     */
    private final String queryDeleteById="DELETE FROM department WHERE id=?";
    @Autowired
    private DepartmentMapper departmentMapper;

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public DepartmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Department> getAll(){
        return jdbcTemplate.query(queryGetAll,departmentMapper);
    }


   public void create(Department department){
           jdbcTemplate.update(queryCreate,department.getId().toString()
                   ,department.getFullName(),department.getShortName(),department.getSupervisor()
                   ,department.getContactNumber(),department.getOrganization().getId().toString());
   }


}
