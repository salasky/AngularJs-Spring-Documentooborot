package com.example.testproject1.dao.baseDocument;

import com.example.testproject1.dao.baseDocument.mapper.BaseDocumentMapper;
import com.example.testproject1.dao.person.mapper.PersonMapper;
import com.example.testproject1.model.documents.BaseDocument;
import com.example.testproject1.model.staff.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BaseDocumentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Запрос на создание записи в таблице base_document
     */
    private final String queryCreate="INSERT INTO base_document VALUES (?,?,?,?,?,?)";
    /**
     * Запрос на получение всех объектов из таблицы base_document
     */
    private final String queryGetAll=
            "SELECT  base_document.id AS base_document_id, base_document.name AS base_document_name, " +
                    "base_document.text AS base_document_text, base_document.reg_number AS base_document_number," +
                    "base_document.creating_date AS base_document_date," +
                    "person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name," +
                    "person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number," +
                    "person.birth_day AS person_birth_day," +
                    "department.id AS department_id, department.full_name AS department_full_name," +
                    "department.short_name AS department_short_name, department.supervisor AS department_supervisor," +
                    "department.contact_number AS department_contact_number, organization.id AS organization_id ," +
                    "organization.full_name AS organization_full_name, organization.short_name AS organization_short_name," +
                    "organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number, " +
                    "job_tittle.id AS job_tittle_id, job_tittle.name AS job_name  " +
                    "FROM base_document " +
                    "INNER JOIN person" +
                    "    ON base_document.author_id=person.id " +
                    "INNER JOIN department " +
                    "    ON person.department_id=department_id " +
                    "INNER JOIN job_tittle " +
                    "    ON person.job_tittle_id=job_tittle.id "+
                    "INNER JOIN organization " +
                    "   ON organization.id=department.organization_id";

    /**
     * Запрос на получение объекта по id из таблицы base_document
     */
    private final String queryGetById=
            "SELECT  base_document.id AS base_document_id, base_document.name AS base_document_name, " +
                    "base_document.text AS base_document_text, base_document.reg_number AS base_document_number," +
                    "base_document.creating_date AS base_document_date," +
                    "person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name," +
                    "person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number," +
                    "person.birth_day AS person_birth_day," +
                    "department.id AS department_id, department.full_name AS department_full_name," +
                    "department.short_name AS department_short_name, department.supervisor AS department_supervisor," +
                    "department.contact_number AS department_contact_number, organization.id AS organization_id ," +
                    "organization.full_name AS organization_full_name, organization.short_name AS organization_short_name," +
                    "organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number, " +
                    "job_tittle.id AS job_tittle_id, job_tittle.name AS job_name  " +
                    "FROM base_document " +
                    "INNER JOIN person" +
                    "    ON base_document.author_id=person.id " +
                    "INNER JOIN department " +
                    "    ON person.department_id=department_id " +
                    "INNER JOIN job_tittle " +
                    "    ON person.job_tittle_id=job_tittle.id "+
                    "INNER JOIN organization " +
                    "   ON organization.id=department.organization_id WHERE base_document.id=?";
    /**
     * Запрос на обновление записи в таблице base_document
     */
    private final String queryUpdate="UPDATE base_document SET name=?, text=?, reg_number=?," +
            " creating_date=?, author_id=? WHERE id=?";

    /**
     * Запрос на удаление всех записей в таблице base_document
     */
    private final String queryDeleteAll="DELETE FROM base_document";
    /**
     * Запрос на удаление записи по id в таблице base_document
     */
    private final String queryDeleteById="DELETE FROM base_document WHERE id=?";

    public void create(BaseDocument baseDocument){
        jdbcTemplate.update(queryCreate,baseDocument.getId().toString(),baseDocument.getName(),baseDocument.getText(),
                baseDocument.getRegNumber(),baseDocument.getCreatingDate(),baseDocument.getAuthor().getId().toString());
    }

    public List<BaseDocument> getAll(){
        return jdbcTemplate.query(queryGetAll,new BaseDocumentMapper());
    }

    public Optional<BaseDocument> getById(String id){
        return jdbcTemplate.query(queryGetById, new BaseDocumentMapper(),id).stream().findFirst();
    }

    public Integer update(BaseDocument baseDocument){
        return jdbcTemplate.update(queryUpdate,baseDocument.getName(),baseDocument.getText(),
                baseDocument.getRegNumber(),baseDocument.getCreatingDate(),baseDocument.getAuthor().getId().toString(),
                baseDocument.getId().toString());
    }

    public void deleteAll(){
        jdbcTemplate.update(queryDeleteAll);
    }

    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(queryDeleteById, id);
        return update;
    }

}
