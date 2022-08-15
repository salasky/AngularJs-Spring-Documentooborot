package com.example.testproject1.service.mappingutils;

import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.dto.document.IncomingDocumentDTO;
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
    public DepartmentDTO mapDepartmentToDto(Department department){
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
    public PersonDTO mapPersonToDto(Person person){
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
    public Person mapDtoToPerson(PersonDTO personDTO){
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
    public IncomingDocumentDTO mapIncomingDocumentToDto(IncomingDocument incomingDocument){
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
    public IncomingDocument mapDtoToIncomingDocument(IncomingDocumentDTO incomingDocumentDTO){
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
}
