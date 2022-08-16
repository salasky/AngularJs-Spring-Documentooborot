package com.example.testproject1.service.facadeservice.person;

import com.example.testproject1.model.dto.staff.PersonDTO;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import com.example.testproject1.service.mappingutils.PersonMapperAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.BatchUpdateException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private PersonMapperAbstract mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonDTO create(PersonDTO entity) {
        Person person = mapper.dtoToPerson(entity);
        return mapper.personToDTO(personCrudService.create(person));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonDTO> getAll() {
        List<Person> personList = personCrudService.getAll();
        return mapper.listToDtoList(personList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<PersonDTO> getById(UUID id) {
        Optional<Person> optionalPerson = personCrudService.getById(id);
        if (optionalPerson.isPresent()) {
            return Optional.ofNullable(mapper.personToDTO(optionalPerson.get()));
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonDTO update(PersonDTO entity) {
        Person person = mapper.dtoToPerson(entity);
        return mapper.personToDTO(personCrudService.update(person));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<PersonDTO> entityList) throws BatchUpdateException {
        personCrudService.saveAll(mapper.dtoListToList(entityList));
    }
}
