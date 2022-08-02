package com.example.testproject1.dao.incomingDocumrnt;

import com.example.testproject1.dao.baseDocument.BaseDocumentRepository;
import com.example.testproject1.dao.incomingDocumrnt.mapper.IncomingDocumentMapper;
import com.example.testproject1.dao.person.PersonRepository;
import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.TaskDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IncomingDocumentRepositoryImpl implements IncomingDocumentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IncomingDocumentMapper incomingDocumentMapper;

    @Autowired
    private BaseDocumentRepository baseDocumentRepository;

    @Autowired
    private PersonRepository personRepository;
    /**
     * Запрос на создание записи в таблице incoming_document
     */
    private final String queryCreate="INSERT INTO incoming_document VALUES (?,?,?,?,?)";

    /**
     * Запрос на получение всех объектов из таблицы incoming_document
     */
    private final String queryGetAll="SELECT   base_document.id AS base_document_id,  " +
            "                    base_document.name AS base_document_name,   " +
            "                    base_document.text AS base_document_text, " +
            "                    base_document.reg_number AS base_document_number,  " +
            "                    base_document.creating_date AS base_document_date, " +
            "                    person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name,  " +
            "                    person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number,  " +
            "                    person.birth_day AS person_birth_day,  " +
            "                    department.id AS department_id, department.full_name AS department_full_name,  " +
            "                    department.short_name AS department_short_name, department.supervisor AS department_supervisor,  " +
            "                    department.contact_number AS department_contact_number, organization.id AS organization_id ,  " +
            "                    organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,  " +
            "                    organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number,   " +
            "                    job_tittle.id AS job_tittle_id, job_tittle.name AS job_name, " +
            "                    incoming_document.number AS incoming_document_number, " +
            "                    incoming_document.date_of_registration AS incoming_document_date_of_registration, " +
            "                    sender.id AS person_sender_id, " +
            "                    sender.first_name AS person_sender_first_name,  " +
            "                    sender.second_name AS person_sender_second_name,  " +
            "                    sender.last_name AS person_sender_last_name,  " +
            "                    sender.photo AS person_sender_photo, " +
            "                    sender.phone_number AS person_sender_phone_number, " +
            "                    sender.birth_day AS person_sender_birth_day,  " +
            "                    departmentsender.id AS department_sender_id, " +
            "                    departmentsender.full_name AS department_sender_full_name,  " +
            "                    departmentsender.short_name AS department_sender_short_name, " +
            "                    departmentsender.supervisor AS department_sender_supervisor,  " +
            "                    departmentsender.contact_number AS department_sender_contact_number, " +
            "                    organizationsender.id AS organization_sender_id ,  " +
            "                    organizationsender.full_name AS organization_sender_full_name,  " +
            "                    organizationsender.short_name AS organization_sender_short_name,  " +
            "                    organizationsender.supervisor AS organization_sender_supervisor, " +
            "                    organizationsender.contact_number AS organization_sender_contact_number,   " +
            "                    jobTittlesender.id AS job_tittle_sender_id, " +
            "                    jobTittlesender.name AS job_sender_name, " +
            "                    destination.id AS person_destination_id, " +
            "                    destination.first_name AS person_destination_first_name,  " +
            "                    destination.second_name AS person_destination_second_name,  " +
            "                    destination.last_name AS person_destination_last_name,  " +
            "                    destination.photo AS person_destination_photo, " +
            "                    destination.phone_number AS person_destination_phone_number, " +
            "                    destination.birth_day AS person_destination_birth_day,  " +
            "                    departmentDestination.id AS department_destination_id, " +
            "                    departmentDestination.full_name AS department_destination_full_name,  " +
            "                    departmentDestination.short_name AS department_destination_short_name, " +
            "                    departmentDestination.supervisor AS department_destination_supervisor,  " +
            "                    departmentDestination.contact_number AS department_destination_contact_number, " +
            "                    organizationDestination.id AS organization_destination_id ,  " +
            "                    organizationDestination.full_name AS organization_destination_full_name,  " +
            "                    organizationDestination.short_name AS organization_destination_short_name,  " +
            "                    organizationDestination.supervisor AS organization_destination_supervisor, " +
            "                    organizationDestination.contact_number AS organization_destination_contact_number,   " +
            "                    jobTittleDestination.id AS job_tittle_destination_id, " +
            "                    jobTittleDestination.name AS job_destination_name " +
            "                    FROM incoming_document  " +
            "                    INNER JOIN base_document  " +
            "                        ON base_document.id=incoming_document.base_document_id " +
            "                    INNER JOIN person  " +
            "                        ON base_document.author_id=person.id   " +
            "                    INNER JOIN department   " +
            "                        ON person.department_id=department_id   " +
            "                    INNER JOIN job_tittle   " +
            "                        ON person.job_tittle_id=job_tittle.id  " +
            "                    INNER JOIN organization   " +
            "                       ON organization.id=department.organization_id " +
            "                    INNER JOIN person AS sender " +
            "                        ON incoming_document.sender_id=sender.id " +
            "                    INNER JOIN department AS departmentSender " +
            "                        ON sender.department_id=departmentSender.id   " +
            "                    INNER JOIN organization AS organizationSender " +
            "                       ON organizationSender.id=departmentSender.organization_id " +
            "                    INNER JOIN job_tittle AS jobTittleSender " +
            "                        ON sender.job_tittle_id=jobTittleSender.id  " +
            "                    INNER JOIN person AS destination " +
            "                        ON incoming_document.destination_id=destination.id " +
            "                    INNER JOIN department AS departmentDestination " +
            "                        ON destination.department_id=departmentDestination.id  " +
            "                    INNER JOIN organization AS organizationDestination " +
            "                       ON organizationDestination.id=departmentDestination.organization_id  " +
            "                    INNER JOIN job_tittle AS jobTittleDestination " +
            "                        ON destination.job_tittle_id=jobTittleDestination.id  ";
    /**
     * Запрос на получение объекта по id из таблицы incoming_document
     */
    private final String queryGetById="SELECT   base_document.id AS base_document_id,  " +
            "                    base_document.name AS base_document_name,   " +
            "                    base_document.text AS base_document_text, " +
            "                    base_document.reg_number AS base_document_number,  " +
            "                    base_document.creating_date AS base_document_date, " +
            "                    person.id AS person_id, person.first_name AS person_first_name, person.second_name AS person_second_name,  " +
            "                    person.last_name AS person_last_name, person.photo AS person_photo, person.phone_number AS person_phone_number,  " +
            "                    person.birth_day AS person_birth_day,  " +
            "                    department.id AS department_id, department.full_name AS department_full_name,  " +
            "                    department.short_name AS department_short_name, department.supervisor AS department_supervisor,  " +
            "                    department.contact_number AS department_contact_number, organization.id AS organization_id ,  " +
            "                    organization.full_name AS organization_full_name, organization.short_name AS organization_short_name,  " +
            "                    organization.supervisor AS organization_supervisor, organization.contact_number AS organization_contact_number,   " +
            "                    job_tittle.id AS job_tittle_id, job_tittle.name AS job_name, " +
            "                    incoming_document.number AS incoming_document_number, " +
            "                    incoming_document.date_of_registration AS incoming_document_date_of_registration, " +
            "                    sender.id AS person_sender_id, " +
            "                    sender.first_name AS person_sender_first_name,  " +
            "                    sender.second_name AS person_sender_second_name,  " +
            "                    sender.last_name AS person_sender_last_name,  " +
            "                    sender.photo AS person_sender_photo, " +
            "                    sender.phone_number AS person_sender_phone_number, " +
            "                    sender.birth_day AS person_sender_birth_day,  " +
            "                    departmentsender.id AS department_sender_id, " +
            "                    departmentsender.full_name AS department_sender_full_name,  " +
            "                    departmentsender.short_name AS department_sender_short_name, " +
            "                    departmentsender.supervisor AS department_sender_supervisor,  " +
            "                    departmentsender.contact_number AS department_sender_contact_number, " +
            "                    organizationsender.id AS organization_sender_id ,  " +
            "                    organizationsender.full_name AS organization_sender_full_name,  " +
            "                    organizationsender.short_name AS organization_sender_short_name,  " +
            "                    organizationsender.supervisor AS organization_sender_supervisor, " +
            "                    organizationsender.contact_number AS organization_sender_contact_number,   " +
            "                    jobTittlesender.id AS job_tittle_sender_id, " +
            "                    jobTittlesender.name AS job_sender_name, " +
            "                    destination.id AS person_destination_id, " +
            "                    destination.first_name AS person_destination_first_name,  " +
            "                    destination.second_name AS person_destination_second_name,  " +
            "                    destination.last_name AS person_destination_last_name,  " +
            "                    destination.photo AS person_destination_photo, " +
            "                    destination.phone_number AS person_destination_phone_number, " +
            "                    destination.birth_day AS person_destination_birth_day,  " +
            "                    departmentDestination.id AS department_destination_id, " +
            "                    departmentDestination.full_name AS department_destination_full_name,  " +
            "                    departmentDestination.short_name AS department_destination_short_name, " +
            "                    departmentDestination.supervisor AS department_destination_supervisor,  " +
            "                    departmentDestination.contact_number AS department_destination_contact_number, " +
            "                    organizationDestination.id AS organization_destination_id ,  " +
            "                    organizationDestination.full_name AS organization_destination_full_name,  " +
            "                    organizationDestination.short_name AS organization_destination_short_name,  " +
            "                    organizationDestination.supervisor AS organization_destination_supervisor, " +
            "                    organizationDestination.contact_number AS organization_destination_contact_number,   " +
            "                    jobTittleDestination.id AS job_tittle_destination_id, " +
            "                    jobTittleDestination.name AS job_destination_name " +
            "                    FROM incoming_document  " +
            "                    INNER JOIN base_document  " +
            "                        ON base_document.id=incoming_document.base_document_id " +
            "                    INNER JOIN person  " +
            "                        ON base_document.author_id=person.id   " +
            "                    INNER JOIN department   " +
            "                        ON person.department_id=department_id   " +
            "                    INNER JOIN job_tittle   " +
            "                        ON person.job_tittle_id=job_tittle.id  " +
            "                    INNER JOIN organization   " +
            "                       ON organization.id=department.organization_id " +
            "                    INNER JOIN person AS sender " +
            "                        ON incoming_document.sender_id=sender.id " +
            "                    INNER JOIN department AS departmentSender " +
            "                        ON sender.department_id=departmentSender.id   " +
            "                    INNER JOIN organization AS organizationSender " +
            "                       ON organizationSender.id=departmentSender.organization_id " +
            "                    INNER JOIN job_tittle AS jobTittleSender " +
            "                        ON sender.job_tittle_id=jobTittleSender.id  " +
            "                    INNER JOIN person AS destination " +
            "                        ON incoming_document.destination_id=destination.id " +
            "                    INNER JOIN department AS departmentDestination " +
            "                        ON destination.department_id=departmentDestination.id  " +
            "                    INNER JOIN organization AS organizationDestination " +
            "                       ON organizationDestination.id=departmentDestination.organization_id  " +
            "                    INNER JOIN job_tittle AS jobTittleDestination " +
            "                        ON destination.job_tittle_id=jobTittleDestination.id WHERE incoming_document.base_document_id=?";
    /**
     * Запрос на обновление записи в таблице incoming_document
     */
    private final String queryUpdate="UPDATE incoming_document SET sender_id=?, destination_id=?, number=?," +
            " date_of_registration=? WHERE incoming_document.base_document_id=?";
    /**
     * Запрос на удаление всех записей в таблице incoming_document
     */
    private final String queryDeleteAll="DELETE FROM incoming_document";
    /**
     * Запрос на удаление записи по id в таблице incoming_document
     */
    private final String queryDeleteById="DELETE FROM incoming_document WHERE base_document_id=?";

    @Override
    public Integer create(IncomingDocument incomingDocument) {
        if (existById(incomingDocument.getId().toString())) {
            return -10;//ИСКЛЮЧЕНИЕ В ADVICE CONTROLLER-СДЕЛАТЬ
        } else {
            BaseDocument baseDocument = new BaseDocument();
            baseDocument.setId(incomingDocument.getId());
            baseDocument.setName(incomingDocument.getName());
            baseDocument.setText(incomingDocument.getText());
            baseDocument.setRegNumber(incomingDocument.getRegNumber());
            baseDocument.setCreatingDate(incomingDocument.getCreatingDate());
            baseDocument.setAuthor(incomingDocument.getAuthor());
            baseDocumentRepository.create(baseDocument);
            personRepository.create(incomingDocument.getSender());
            personRepository.create(incomingDocument.getDestination());
            return jdbcTemplate.update(queryCreate, incomingDocument.getId().toString(),
                    incomingDocument.getSender().getId().toString(),
                    incomingDocument.getDestination().getId().toString(),
                    incomingDocument.getNumber(), incomingDocument.getDateOfRegistration());
        }
    }

    @Override
    public List<IncomingDocument> getAll(){
        return jdbcTemplate.query(queryGetAll,incomingDocumentMapper);
    }

    @Override
    public Optional<IncomingDocument> getById(String id){
        return jdbcTemplate.query(queryGetById, incomingDocumentMapper,id)
                .stream().findFirst();
    }
    @Override
    public Integer update(IncomingDocument incomingDocument){
        return jdbcTemplate.update(queryUpdate,incomingDocument.getSender().getId().toString(),
                incomingDocument.getDestination().getId().toString(),incomingDocument.getNumber(),
                incomingDocument.getDateOfRegistration(),incomingDocument.getId().toString());
    }
    @Override
    public Integer deleteAll(){
        return jdbcTemplate.update(queryDeleteAll);
    }
    @Override
    public Integer deleteById(String id) {
        return jdbcTemplate.update(queryDeleteById, id);
    }
    @Override
    public boolean existById(String uuid) {
        Optional<IncomingDocument> incomingDocument= jdbcTemplate.query(queryGetById,incomingDocumentMapper,uuid).stream().findFirst();
        if (incomingDocument.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }
}

