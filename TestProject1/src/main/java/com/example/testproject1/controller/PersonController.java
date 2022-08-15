package com.example.testproject1.controller;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.dto.MessageResponseDTO;
import com.example.testproject1.model.dto.staff.PersonDTO;
import com.example.testproject1.model.dto.staff.PersonDtoListCRUD;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.BatchUpdateException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/persons")
public class PersonController {
    /**
     * Сервис для работы с person
     */
    @Autowired
    private CrudService<Person> personCrudService;
    /**
     * Фасадный Сервис для работы с Person
     */

    @Autowired
    private CrudFacadeService<PersonDTO> personDTOPersonCrudFacadeService;

    /**
     * Метод получения сущностей
     */
    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(personDTOPersonCrudFacadeService.getAll());
    }

    /**
     * Метод получения сущности по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (RuntimeException e) {
            throw new DocflowRuntimeApplicationException("Введен не валидный UUID");
        }
        Optional<PersonDTO> person = personDTOPersonCrudFacadeService.getById(uuid);
        if (person.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(person.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления сущности
     */
    @PostMapping("/add")
    public ResponseEntity<PersonDTO> addOrganization(@Valid @RequestBody PersonDTO personDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personDTOPersonCrudFacadeService.create(personDTO));
    }

    /**
     * Метод сохранения List сущностей
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@Valid @RequestBody PersonDtoListCRUD personDtoListCRUD) throws BatchUpdateException {
        personDTOPersonCrudFacadeService.saveAll(personDtoListCRUD.getPersonList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Persons успешно сохранены"));
    }

    /**
     * Метод обновления сущностей
     */
    @PutMapping("/update")
    public ResponseEntity<PersonDTO> update(@Valid @RequestBody PersonDTO personDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(personDTOPersonCrudFacadeService.update(personDTO));
    }

    /**
     * Метод удаления сущности по id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (RuntimeException e) {
            throw new DocflowRuntimeApplicationException("Введен не валидный UUID");
        }
        personCrudService.deleteById(uuid);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Person успешно удален"));
    }

    /**
     * Метод удаления всех сущностей
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteById() {
        personCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Persons успешно удалены"));
    }
}
