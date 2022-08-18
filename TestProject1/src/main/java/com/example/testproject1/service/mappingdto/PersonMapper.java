package com.example.testproject1.service.mappingdto;

import com.example.testproject1.model.dto.staffdto.PersonDTO;
import com.example.testproject1.model.staff.Person;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public abstract class PersonMapper {
    @Mapping(target = "jobTittleId", source = "person.jobTittle.id")
    @Mapping(target = "departmentId", source = "person.department.id")
    public abstract PersonDTO personToDTO(Person person);

    @Mapping(target = "jobTittle.id", source = "personDTO.jobTittleId")
    @Mapping(target = "department.id", source = "personDTO.departmentId")
    public abstract Person dtoToPerson(PersonDTO personDTO);

    public abstract List<PersonDTO> listToDtoList(List<Person> taskDocumentList);

    public abstract List<Person> dtoListToList(List<PersonDTO> taskDocumentList);
}
