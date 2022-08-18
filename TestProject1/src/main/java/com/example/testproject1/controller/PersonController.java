package com.example.testproject1.controller;

import com.example.testproject1.model.dto.staffdto.PersonDTO;
import com.example.testproject1.model.dto.staffdto.PersonDtoListForMapping;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.model.utility.MessageResponseDTO;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс контроллер для сущности {@link Person}
 *
 * @author smigranov
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private CrudService<Person> personCrudService;
    @Autowired
    private CrudFacadeService<PersonDTO> personDTOPersonCrudFacadeService;

    /**
     * Метод получения Persons
     */
    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(personDTOPersonCrudFacadeService.getAll());
    }

    /**
     * Метод получения Person по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable UUID id) {
        Optional<PersonDTO> person = personDTOPersonCrudFacadeService.getById(id);
        if (person.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(person.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления Person
     */
    @PostMapping("/add")
    public ResponseEntity<PersonDTO> addPerson(@Valid @RequestBody PersonDTO personDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personDTOPersonCrudFacadeService.create(personDTO));
    }

    /**
     * Метод сохранения List Person
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@Valid @RequestBody PersonDtoListForMapping personDtoListForMapping) {
        personDTOPersonCrudFacadeService.saveAll(personDtoListForMapping.getPersonList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Persons успешно сохранены"));
    }

    /**
     * Метод обновления Person
     */
    @PutMapping("/update")
    public ResponseEntity<PersonDTO> update(@Valid @RequestBody PersonDTO personDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(personDTOPersonCrudFacadeService.update(personDTO));
    }

    /**
     * Метод удаления Person по id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable UUID id) {
        personCrudService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Person успешно удален"));
    }

    /**
     * Метод удаления всех Person
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteAll() {
        personCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Persons успешно удалены"));
    }
}
