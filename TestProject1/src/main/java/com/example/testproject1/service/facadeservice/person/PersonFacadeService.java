package com.example.testproject1.service.facadeservice.person;

import com.example.testproject1.model.dto.staffdto.PersonDTO;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import com.example.testproject1.service.mappingdto.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private CrudService<Person> personCrudService;

    @Autowired
    private PersonMapper mapper;

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
        return Optional.ofNullable(mapper.personToDTO(personCrudService.getById(id).get()));
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
    public void saveAll(List<PersonDTO> entityList) {
        personCrudService.saveAll(mapper.dtoListToList(entityList));
    }
}
