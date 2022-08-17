package com.example.testproject1.controller;


import com.example.testproject1.model.utility.MessageResponseDTO;
import com.example.testproject1.model.dto.staff.OrganizationListDTO;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.dbservice.CrudService;
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
 * Класс контроллер для сущности {@link Organization}
 *
 * @author smigranov
 */
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private CrudService<Organization> organizationCrudService;

    /**
     * Метод получения Organizations
     */
    @GetMapping
    public ResponseEntity<List<Organization>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(organizationCrudService.getAll());
    }

    /**
     * Метод получения Organization по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Organization> getById(@PathVariable UUID id) {
        Optional<Organization> organization = organizationCrudService.getById(id);
        if (organization.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(organization.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления Organization
     */
    @PostMapping("/add")
    public ResponseEntity<Organization> addOrganization(@Valid @RequestBody Organization organization) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationCrudService.create(organization));
    }

    /**
     * Метод сохранения List Organization
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@RequestBody OrganizationListDTO organizationList) {
        organizationCrudService.saveAll(organizationList.getOrganizationList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Организации успешно сохранены"));
    }

    /**
     * Метод обновления Organization
     */
    @PutMapping("/update")
    public ResponseEntity<Organization> update(@Valid @RequestBody Organization organization) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationCrudService.update(organization));
    }

    /**
     * Метод удаления Organization по id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable UUID id) {
        organizationCrudService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Организация успешно удалена"));
    }

    /**
     * Метод удаления всех Organization
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteAll() {
        organizationCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Организации успешно удалены"));
    }
}
