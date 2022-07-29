package com.example.testproject1.dao.department;

import com.example.testproject1.dao.department.mapper.DepartmentMapper;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepository {
    /**
     * Запрос на получение всех объектов из таблицы department
     */
    private final String queryGetAll="SELECT department.id AS department_id, department.full_name AS department_full_name," +
            " department.short_name AS department_short_name, department.supervisor AS department_supervisor," +
            " department.contact_number AS department_contact_number, organization.id AS organization_id ," +
            " organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,\n" +
            "organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number\n" +
            "FROM department INNER JOIN organization ON department.organization_id=organization.id";
    /**
     * Запрос на получение объекта по id из таблицы department
     */
    private final String queryGetById="SELECT department.id AS department_id, department.full_name AS department_full_name," +
            " department.short_name AS department_short_name, department.supervisor AS department_supervisor," +
            " department.contact_number AS department_contact_number, organization.id AS organization_id ," +
            " organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,\n" +
            "organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number\n" +
            "FROM department INNER JOIN organization ON department.organization_id=organization.id WHERE department.id=?";
    /**
     * Запрос на создание записи в таблице department
     */
    private final String queryCreate="INSERT INTO department VALUES (?,?,?,?,?,?)";
    /**
     * Запрос на удаление всех записей в таблице department
     */
    private final String queryDeleteAll="DELETE FROM department";
    /**
     * Запрос на удаление записи по id в таблице department
     */
    private final String queryDeleteById="DELETE FROM department WHERE id=?";
    /**
     * Запрос на обновление записи по id в таблице department
     */
    private final String queryUpdate="UPDATE department SET full_name=?, short_name=?," +
            " supervisor=?, contact_number=?, organization_id=? WHERE id=?";
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Department> getAll(){
        return jdbcTemplate.query(queryGetAll,departmentMapper);
    }


   public void create(Department department){
           jdbcTemplate.update(queryCreate,department.getId().toString()
                   ,department.getFullName(),department.getShortName(),department.getSupervisor()
                   ,department.getContactNumber(),department.getOrganization().getId().toString());
   }

    public Integer update(Department department){
        return jdbcTemplate.update(queryUpdate,department.getFullName(),department.getShortName(),
                department.getSupervisor(),department.getContactNumber(),department.getOrganization().getId().toString(),
                department.getId().toString());
    }

   public Optional<Department> getById(String id){
      return jdbcTemplate.query(queryGetById, new DepartmentMapper(),id).stream().findFirst();
   }

    public void deleteAll(){
        jdbcTemplate.update(queryDeleteAll);
    }

    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(queryDeleteById, id);
        return update;
    }

}
