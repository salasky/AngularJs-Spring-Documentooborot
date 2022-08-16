package com.example.testproject1.service.facadeservice.person;

import com.example.testproject1.model.dto.staff.PersonDTO;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import com.example.testproject1.service.mappingutils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.BatchUpdateException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Класс реализующий интерфейс {@link CrudFacadeService}. Для выполнения CRUD операций к базе данных.
 *
 * @author smigranov
 */
@Service
public class PersonFacadeService implements CrudFacadeService<PersonDTO> {
    /**
     * Person сервис
     */
    @Autowired
    private CrudService<Person> personCrudService;
    /**
     * Mapper Entity to DTO
     */
    @Autowired
    private MappingUtils mappingUtils;

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonDTO create(PersonDTO entity) {
        Person person = mappingUtils.mapDtoToPerson(entity);
        return mappingUtils.mapPersonToDto(personCrudService.create(person));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonDTO> getAll() {
        List<Person> personList = personCrudService.getAll();
        return personList.stream().map(s -> mappingUtils.mapPersonToDto(s)).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<PersonDTO> getById(UUID id) {
        Optional<Person> optionalPerson = personCrudService.getById(id);
        if (optionalPerson.isPresent()) {
            return Optional.ofNullable(mappingUtils.mapPersonToDto(optionalPerson.get()));
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonDTO update(PersonDTO entity) {
        Person person = mappingUtils.mapDtoToPerson(entity);
        return mappingUtils.mapPersonToDto(personCrudService.update(person));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<PersonDTO> entityList) throws BatchUpdateException {
        List<Person> personList = entityList.stream().map(s -> mappingUtils.mapDtoToPerson(s)).collect(Collectors.toList());
        personCrudService.saveAll(personList);
    }
}
