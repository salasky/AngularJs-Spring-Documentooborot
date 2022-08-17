package com.example.testproject1.controller;


import com.example.testproject1.model.dto.staffdto.OrganizationDTO;
import com.example.testproject1.model.dto.staffdto.OrganizationListForMapping;
import com.example.testproject1.model.staff.Organization;
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
 * Класс контроллер для сущности {@link Organization}
 *
 * @author smigranov
 */
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private CrudService<Organization> organizationCrudService;
    @Autowired
    private CrudFacadeService<OrganizationDTO> organizationCrudFacadeService;

    /**
     * Метод получения Organizations
     */
    @GetMapping
    public ResponseEntity<List<OrganizationDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(organizationCrudFacadeService.getAll());
    }

    /**
     * Метод получения Organization по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDTO> getById(@PathVariable UUID id) {
        Optional<OrganizationDTO> organizationDTO = organizationCrudFacadeService.getById(id);
        if (organizationDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(organizationDTO.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления Organization
     */
    @PostMapping("/add")
    public ResponseEntity<OrganizationDTO> addOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationCrudFacadeService.create(organizationDTO));
    }

    /**
     * Метод сохранения List Organization
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@RequestBody OrganizationListForMapping organizationList) {
        organizationCrudFacadeService.saveAll(organizationList.getOrganizationList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Organization успешно сохранены"));
    }

    /**
     * Метод обновления Organization
     */
    @PutMapping("/update")
    public ResponseEntity<OrganizationDTO> update(@Valid @RequestBody OrganizationDTO organizationDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationCrudFacadeService.update(organizationDTO));
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
