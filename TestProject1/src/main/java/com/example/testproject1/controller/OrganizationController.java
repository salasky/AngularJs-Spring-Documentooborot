package com.example.testproject1.controller;


import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.dto.MessageResponseDTO;
import com.example.testproject1.model.dto.OrganizationListDTO;
import com.example.testproject1.model.staff.Index;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.dbservice.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

/**
 * Класс контроллер для сущности {@link Organization}
 *
 * @author smigranov
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizations")
public class OrganizationController {
    /**
     * Сервис для работы с организациями
     */
    @Autowired
    private CrudService<Organization> organizationCrudService;

    /**
     * Метод получения сущностей
     */
    @GetMapping
    public ResponseEntity<List<Organization>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(organizationCrudService.getAll());
    }

    /**
     * Метод получения сущности по id
     */
    @GetMapping("/id")
    public ResponseEntity<Organization> getById(@Valid @RequestBody Index index) {
        try {
            UUID id = UUID.fromString(index.getId());
            Optional<Organization> organization = organizationCrudService.getById(id);
            if (organization.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(organization.get());
            }
        } catch (RuntimeException e) {
            throw new DocflowRuntimeApplicationException("Введен не валидный UUID");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Метод добавления сущности
     */
    @PostMapping("/add")
    public ResponseEntity<Organization> addOrganization(@Valid @RequestBody Organization organization) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationCrudService.create(organization));
    }

    /**
     * Метод сохранения List сущностей
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@RequestBody OrganizationListDTO organizationList) throws BatchUpdateException {
        organizationCrudService.saveALL(organizationList.getOrganizationList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Организации успешно сохранены"));
    }

    /**
     * Метод обновления сущностей
     */
    @PutMapping("/update")
    public ResponseEntity<Organization> update(@Valid @RequestBody Organization organization) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationCrudService.update(organization));
    }

    /**
     * Метод удаления сущности по id
     */
    @DeleteMapping("/delete")
    public ResponseEntity<MessageResponseDTO> deleteById(@Valid @RequestBody Index index) {
        try {
            UUID id = UUID.fromString(index.getId());
            organizationCrudService.deleteById(id);
        } catch (RuntimeException e) {
            throw new DocflowRuntimeApplicationException("Введен не валидный UUID");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Организация успешно удалена"));
    }

    /**
     * Метод удаления всех сущностей
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteById() {
        organizationCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Организации успешно удалены"));
    }
}
