package com.example.testproject1.controller;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.dto.MessageResponseDTO;
import com.example.testproject1.model.dto.PersonListDTO;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
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
     * Метод получения сущностей
     */
    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(personCrudService.getAll());
    }

    /**
     * Метод получения сущности по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (RuntimeException e) {
            throw new DocflowRuntimeApplicationException("Введен не валидный UUID");
        }
        Optional<Person> person = personCrudService.getById(uuid);
        if (person.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(person.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления сущности
     */
    @PostMapping("/add")
    public ResponseEntity<Person> addOrganization(@Valid @RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personCrudService.create(person));
    }

    /**
     * Метод сохранения List сущностей
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@Valid @RequestBody PersonListDTO personListDTO) throws BatchUpdateException {
        personCrudService.saveALL(personListDTO.getPersonList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Persons успешно сохранены"));
    }

    /**
     * Метод обновления сущностей
     */
    @PutMapping("/update")
    public ResponseEntity<Person> update(@Valid @RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.OK).body(personCrudService.update(person));
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
