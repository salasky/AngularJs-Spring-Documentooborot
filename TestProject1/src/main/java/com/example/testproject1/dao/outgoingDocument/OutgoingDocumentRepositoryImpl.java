package com.example.testproject1.dao.outgoingDocument;

import com.example.testproject1.dao.baseDocument.BaseDocumentRepository;
import com.example.testproject1.dao.outgoingDocument.mapper.OutgoingDocumentMapper;
import com.example.testproject1.dao.person.PersonRepository;
import com.example.testproject1.dao.taskDocument.TaskDocumentRepositoryImpl;
import com.example.testproject1.exception.BaseDocumentExistInDb;
import com.example.testproject1.exception.DocumentExistInDb;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.service.dbService.baseDocument.BaseDocumentService;
import com.example.testproject1.service.dbService.person.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OutgoingDocumentRepositoryImpl implements OutgoingDocumentRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(OutgoingDocumentRepositoryImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OutgoingDocumentMapper outgoingDocumentMapper;
    @Autowired
    private BaseDocumentService baseDocumentService;
    @Autowired
    private PersonService personService;
    /**
     * Запрос на создание записи в таблице outgoing_document
     */
    private final String queryCreate="INSERT INTO outgoing_document VALUES (?,?,?)";
    /**
     * Запрос на получение всех объектов из таблицы outgoing_document
     */
    private final String queryGetAll="SELECT   base_document.id AS base_document_id,  " +
            "                    base_document.name AS base_document_name,  " +
            "                    base_document.text AS base_document_text,  " +
            "                    base_document.reg_number AS base_document_number,   " +
            "                    base_document.creating_date AS base_document_date,  " +
            "                    person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name,   " +
            "                    person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number,   " +
            "                    person.birth_day AS person_birth_day,   " +
            "                    department.id AS department_id, department.full_name AS department_full_name,   " +
            "                    department.short_name AS department_short_name, department.supervisor AS department_supervisor,   " +
            "                    department.contact_number AS department_contact_number, organization.id AS organization_id ,   " +
            "                    organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,   " +
            "                    organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number,    " +
            "                    job_tittle.id AS job_tittle_id, job_tittle.name AS job_name,  " +
            "                    outgoing_document.delivery_type AS outgoing_delivery_type,  " +
            "                    sender.id AS person_sender_id,  " +
            "                    sender.first_name AS person_sender_first_name,   " +
            "                    sender.second_name AS person_sender_second_name,   " +
            "                    sender.last_name AS person_sender_last_name,   " +
            "                    sender.photo AS person_sender_photo,  " +
            "                    sender.phone_number AS person_sender_phone_number,  " +
            "                    sender.birth_day AS person_sender_birth_day,   " +
            "                    departmentsender.id AS department_sender_id,  " +
            "                    departmentsender.full_name AS department_sender_full_name,   " +
            "                    departmentsender.short_name AS department_sender_short_name,  " +
            "                    departmentsender.supervisor AS department_sender_supervisor,   " +
            "                    departmentsender.contact_number AS department_sender_contact_number,  " +
            "                    organizationsender.id AS organization_sender_id ,   " +
            "                    organizationsender.full_name AS organization_sender_full_name,   " +
            "                    organizationsender.short_name AS organization_sender_short_name,   " +
            "                    organizationsender.supervisor AS organization_sender_supervisor,  " +
            "                    organizationsender.contact_number AS organization_sender_contact_number,    " +
            "                    jobTittlesender.id AS job_tittle_sender_id,  " +
            "                    jobTittlesender.name AS job_sender_name  " +
            "                    FROM outgoing_document   " +
            "                    INNER JOIN base_document   " +
            "                        ON base_document.id=outgoing_document.base_document_id  " +
            "                    INNER JOIN person   " +
            "                        ON base_document.author_id=person.id    " +
            "                    INNER JOIN department    " +
            "                        ON person.department_id=department_id    " +
            "                    INNER JOIN job_tittle    " +
            "                        ON person.job_tittle_id=job_tittle.id   " +
            "                    INNER JOIN organization    " +
            "                       ON organization.id=department.organization_id  " +
            "                    INNER JOIN person AS sender  " +
            "                        ON outgoing_document.sender_id=sender.id  " +
            "                    INNER JOIN department AS departmentSender  " +
            "                        ON sender.department_id=departmentSender.id    " +
            "                    INNER JOIN organization AS organizationSender  " +
            "                       ON organizationSender.id=departmentSender.organization_id  " +
            "                    INNER JOIN job_tittle AS jobTittleSender  " +
            "                        ON sender.job_tittle_id=jobTittleSender.id ";
    /**
     * Запрос на получение объекта по id из таблицы outgoing_document
     */
    private final String queryGetById="SELECT   base_document.id AS base_document_id,  " +
            "                    base_document.name AS base_document_name,  " +
            "                    base_document.text AS base_document_text,  " +
            "                    base_document.reg_number AS base_document_number,   " +
            "                    base_document.creating_date AS base_document_date,  " +
            "                    person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name,   " +
            "                    person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number,   " +
            "                    person.birth_day AS person_birth_day,   " +
            "                    department.id AS department_id, department.full_name AS department_full_name,   " +
            "                    department.short_name AS department_short_name, department.supervisor AS department_supervisor,   " +
            "                    department.contact_number AS department_contact_number, organization.id AS organization_id ,   " +
            "                    organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,   " +
            "                    organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number,    " +
            "                    job_tittle.id AS job_tittle_id, job_tittle.name AS job_name,  " +
            "                    outgoing_document.delivery_type AS outgoing_delivery_type,  " +
            "                    sender.id AS person_sender_id,  " +
            "                    sender.first_name AS person_sender_first_name,   " +
            "                    sender.second_name AS person_sender_second_name,   " +
            "                    sender.last_name AS person_sender_last_name,   " +
            "                    sender.photo AS person_sender_photo,  " +
            "                    sender.phone_number AS person_sender_phone_number,  " +
            "                    sender.birth_day AS person_sender_birth_day,   " +
            "                    departmentsender.id AS department_sender_id,  " +
            "                    departmentsender.full_name AS department_sender_full_name,   " +
            "                    departmentsender.short_name AS department_sender_short_name,  " +
            "                    departmentsender.supervisor AS department_sender_supervisor,   " +
            "                    departmentsender.contact_number AS department_sender_contact_number,  " +
            "                    organizationsender.id AS organization_sender_id ,   " +
            "                    organizationsender.full_name AS organization_sender_full_name,   " +
            "                    organizationsender.short_name AS organization_sender_short_name,   " +
            "                    organizationsender.supervisor AS organization_sender_supervisor,  " +
            "                    organizationsender.contact_number AS organization_sender_contact_number,    " +
            "                    jobTittlesender.id AS job_tittle_sender_id,  " +
            "                    jobTittlesender.name AS job_sender_name  " +
            "                    FROM outgoing_document   " +
            "                    INNER JOIN base_document   " +
            "                        ON base_document.id=outgoing_document.base_document_id  " +
            "                    INNER JOIN person   " +
            "                        ON base_document.author_id=person.id    " +
            "                    INNER JOIN department    " +
            "                        ON person.department_id=department_id    " +
            "                    INNER JOIN job_tittle    " +
            "                        ON person.job_tittle_id=job_tittle.id   " +
            "                    INNER JOIN organization    " +
            "                       ON organization.id=department.organization_id  " +
            "                    INNER JOIN person AS sender  " +
            "                        ON outgoing_document.sender_id=sender.id  " +
            "                    INNER JOIN department AS departmentSender  " +
            "                        ON sender.department_id=departmentSender.id    " +
            "                    INNER JOIN organization AS organizationSender  " +
            "                       ON organizationSender.id=departmentSender.organization_id  " +
            "                    INNER JOIN job_tittle AS jobTittleSender  " +
            "                        ON sender.job_tittle_id=jobTittleSender.id WHERE outgoing_document.base_document_id=?";
    /**
     * Запрос на обновление записи в таблице outgoing_document
     */
    private final String queryUpdate="UPDATE outgoing_document SET sender_id=?, delivery_type=?" +
            " WHERE outgoing_document.base_document_id=?";
    /**
     * Запрос на удаление всех записей в таблице outgoing_document
     */
    private final String queryDeleteAll="DELETE FROM outgoing_document";
    /**
     * Запрос на удаление записи по id в таблице outgoing_document
     */
    private final String queryDeleteById="DELETE FROM outgoing_document WHERE base_document_id=?";

    @Override
    public Integer create(OutgoingDocument outgoingDocument){
        try {
            isNotExistElseThrow(outgoingDocument);
            BaseDocument baseDocument=new BaseDocument();
            baseDocument.setId(outgoingDocument.getId());
            baseDocument.setName(outgoingDocument.getName());
            baseDocument.setText(outgoingDocument.getText());
            baseDocument.setRegNumber(outgoingDocument.getRegNumber());
            baseDocument.setCreatingDate(outgoingDocument.getCreatingDate());
            baseDocument.setAuthor(outgoingDocument.getAuthor());
            baseDocumentService.create(baseDocument);
            personService.create(outgoingDocument.getSender());
            return jdbcTemplate.update(queryCreate,outgoingDocument.getId().toString(),
                    outgoingDocument.getSender().getId().toString(),
                    outgoingDocument.getDeliveryType().toString());
        } catch (DocumentExistInDb e) {
            LOGGER.info(e.toString());
            return 0;
        }
    }
    public void isNotExistElseThrow(OutgoingDocument outgoingDocument) throws DocumentExistInDb {
        if(existById(outgoingDocument.getId().toString())){
            throw new DocumentExistInDb(outgoingDocument.getId().toString());
        }
    }
    @Override
    public List<OutgoingDocument> getAll(){
        return jdbcTemplate.query(queryGetAll,outgoingDocumentMapper);
    }

    @Override
    public Optional<OutgoingDocument> getById(String id){
        return jdbcTemplate.query(queryGetById, outgoingDocumentMapper,id)
                .stream().findFirst();
    }
    @Override
    public Integer update(OutgoingDocument outgoingDocument){
        return jdbcTemplate.update(queryUpdate,outgoingDocument.getSender().getId().toString(),
                outgoingDocument.getDeliveryType().toString(),outgoingDocument.getId().toString());
    }
    @Override
    public Integer deleteAll(){
        return jdbcTemplate.update(queryDeleteAll);
    }
    @Override
    public Integer deleteById(String id) {
        int update = jdbcTemplate.update(queryDeleteById, id);
        return update;
    }
    @Override
    public boolean existById(String uuid) {
        Optional<OutgoingDocument> outgoingDocument= jdbcTemplate.query(queryGetById,outgoingDocumentMapper,uuid).stream().findFirst();
        return outgoingDocument.isPresent();
    }
}
