package com.example.testproject1.service.mappingutils;

import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.dto.document.IncomingDocumentDTO;
import com.example.testproject1.model.dto.document.OutgoingDocumentDTO;
import com.example.testproject1.model.dto.document.TaskDocumentDTO;
import com.example.testproject1.model.dto.staff.DepartmentDTO;
import com.example.testproject1.model.dto.staff.PersonDTO;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import org.springframework.stereotype.Service;

/**
 * Класс для маппинга Entity в DTO,из dto в entity
 *
 * @author smigranov
 */
@Service
public class MappingUtils {
    /**
     * Department из entity в dto
     */
    public DepartmentDTO mapDepartmentToDto(Department department) {
        return DepartmentDTO.newBuilder()
                .setId(department.getId())
                .setFullName(department.getFullName())
                .setShortName(department.getShortName())
                .setSupervisor(department.getSupervisor())
                .setContactNumber(department.getContactNumber())
                .setOrganization(department.getOrganization()).build();
    }

    /**
     * Department из dto в entity
     */
    public Department mapDtoToDepartment(DepartmentDTO departmentDTO) {
        return Department.newBuilder()
                .setId(departmentDTO.getId())
                .setFullName(departmentDTO.getFullName())
                .setShortName(departmentDTO.getShortName())
                .setSupervisor(departmentDTO.getSupervisor())
                .setContactNumber(departmentDTO.getContactNumber())
                .setOrganization(Organization.newBuilder().setId(departmentDTO.getOrganizationId()).build()).build();
    }

    /**
     * Person из entity в dto
     */
    public PersonDTO mapPersonToDto(Person person) {
        return PersonDTO.newBuilder()
                .setId(person.getId())
                .setFirstName(person.getFirstName())
                .setSecondName(person.getSecondName())
                .setLastName(person.getLastName())
                .setDepartment(person.getDepartment())
                .setJobTittle(person.getJobTittle())
                .setPhoneNumber(person.getPhoneNumber())
                .setPhoto(person.getPhoto())
                .setBirthDay(person.getBirthDay()).build();
    }

    /**
     * Person из dto в entity
     */
    public Person mapDtoToPerson(PersonDTO personDTO) {
        return Person.newBuilder()
                .setId(personDTO.getId())
                .setFirstName(personDTO.getFirstName())
                .setSecondName(personDTO.getSecondName())
                .setLastName(personDTO.getLastName())
                .setDepartment(Department.newBuilder().setId(personDTO.getDepartmentId()).build())
                .setJobTittle(JobTittle.newBuilder().setUuid(personDTO.getJobTittleId()).build())
                .setPhoneNumber(personDTO.getPhoneNumber())
                .setPhoto(personDTO.getPhoto())
                .setBirthDay(personDTO.getBirthDay()).build();
    }

    /**
     * IncomingDocument из entity в dto
     */
    public IncomingDocumentDTO mapIncomingDocumentToDto(IncomingDocument incomingDocument) {
        return (IncomingDocumentDTO) IncomingDocumentDTO.newBuilder()
                .setIncomingDocumentDate(incomingDocument.getDateOfRegistration())
                .setIncomingDocumentNumber(incomingDocument.getNumber())
                .setIncomingDestination(incomingDocument.getDestination())
                .setIncomingSender(incomingDocument.getSender())
                .setId(incomingDocument.getId())
                .setName(incomingDocument.getName())
                .setText(incomingDocument.getText())
                .setAuthor(incomingDocument.getAuthor())
                .setRegNumber(incomingDocument.getRegNumber())
                .setDate(incomingDocument.getCreatingDate()).build();
    }

    /**
     * IncomingDocument из dto в entity
     */
    public IncomingDocument mapDtoToIncomingDocument(IncomingDocumentDTO incomingDocumentDTO) {
        return (IncomingDocument) IncomingDocument.newBuilder()
                .setIncomingDocumentDate(incomingDocumentDTO.getDateOfRegistration())
                .setIncomingDocumentNumber(incomingDocumentDTO.getNumber())
                .setIncomingDestination(Person.newBuilder().setId(incomingDocumentDTO.getDestinationId()).build())
                .setIncomingSender(Person.newBuilder().setId(incomingDocumentDTO.getSenderId()).build())
                .setDocId(incomingDocumentDTO.getId())
                .setDocName(incomingDocumentDTO.getName())
                .setDocText(incomingDocumentDTO.getText())
                .setDocAuthor(Person.newBuilder().setId(incomingDocumentDTO.getAuthorId()).build())
                .setDocRegNumber(incomingDocumentDTO.getRegNumber())
                .setDocDate(incomingDocumentDTO.getCreatingDate()).build();
    }

    /**
     * OutgoingDocument из entity в dto
     */
    public OutgoingDocumentDTO mapOutgoingDocumentToDto(OutgoingDocument outgoingDocument) {
        return (OutgoingDocumentDTO) OutgoingDocumentDTO.newBuilder()
                .setSender(outgoingDocument.getSender())
                .setDocumentDeliveryType(outgoingDocument.getDeliveryType())
                .setId(outgoingDocument.getId())
                .setName(outgoingDocument.getName())
                .setText(outgoingDocument.getText())
                .setAuthor(outgoingDocument.getAuthor())
                .setRegNumber(outgoingDocument.getRegNumber())
                .setDate(outgoingDocument.getCreatingDate()).build();
    }

    /**
     * OutgoingDocument из dto в entity
     */
    public OutgoingDocument mapDtoToOutgoingDocument(OutgoingDocumentDTO outgoingDocumentDTO) {
        return (OutgoingDocument) OutgoingDocument.newBuilder()
                .setDocSender(Person.newBuilder().setId(outgoingDocumentDTO.getSenderId()).build())
                .setDocDeliveryType(outgoingDocumentDTO.getDeliveryType())
                .setDocId(outgoingDocumentDTO.getId())
                .setDocName(outgoingDocumentDTO.getName())
                .setDocText(outgoingDocumentDTO.getText())
                .setDocAuthor(Person.newBuilder().setId(outgoingDocumentDTO.getAuthorId()).build())
                .setDocRegNumber(outgoingDocumentDTO.getRegNumber())
                .setDocDate(outgoingDocumentDTO.getCreatingDate()).build();
    }
    /**
     * TaskDocument из entity в dto
     */
    public TaskDocumentDTO mapTaskDocumentToDto(TaskDocument taskDocument) {
        return (TaskDocumentDTO) TaskDocumentDTO.newBuilder()
                .setTaskControlPerson(taskDocument.getControlPerson())
                .setTaskDate(taskDocument.getOutDate())
                .setTaskExecPeriod(taskDocument.getExecPeriod())
                .setTaskResponsPerson(taskDocument.getResponsible())
                .setTaskSignOfControl(taskDocument.getSignOfControl())
                .setId(taskDocument.getId())
                .setName(taskDocument.getName())
                .setText(taskDocument.getText())
                .setAuthor(taskDocument.getAuthor())
                .setRegNumber(taskDocument.getRegNumber())
                .setDate(taskDocument.getCreatingDate()).build();
    }

    /**
     * TaskDocument из dto в entity
     */
    public TaskDocument mapDtoToTaskDocument(TaskDocumentDTO taskDocumentDTO) {
        return (TaskDocument) TaskDocument.newBuilder()
                .setTaskControlPerson(Person.newBuilder().setId(taskDocumentDTO.getControlPersonId()).build())
                .setTaskDate(taskDocumentDTO.getOutDate())
                .setTaskExecPeriod(taskDocumentDTO.getExecPeriod())
                .setTaskResponsPerson(Person.newBuilder().setId(taskDocumentDTO.getResponsibleId()).build())
                .setTaskSignOfControl(taskDocumentDTO.getSignOfControl())
                .setDocId(taskDocumentDTO.getId())
                .setDocName(taskDocumentDTO.getName())
                .setDocText(taskDocumentDTO.getText())
                .setDocAuthor(Person.newBuilder().setId(taskDocumentDTO.getAuthorId()).build())
                .setDocRegNumber(taskDocumentDTO.getRegNumber())
                .setDocDate(taskDocumentDTO.getCreatingDate()).build();
    }
}
