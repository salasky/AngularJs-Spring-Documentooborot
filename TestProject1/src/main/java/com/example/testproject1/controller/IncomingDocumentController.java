package com.example.testproject1.controller;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.dto.DepartmentListDTO;
import com.example.testproject1.model.dto.IncomingListDTO;
import com.example.testproject1.model.dto.MessageResponseDTO;
import com.example.testproject1.model.staff.Department;
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
@RequestMapping("/incomingdocuments")
public class IncomingDocumentController {
    /**
     * Сервис для работы с Department
     */
    @Autowired
    private CrudService<IncomingDocument> incomingDocumentCrudService;
    /**
     * Метод получения сущностей
     */
    @GetMapping
    public ResponseEntity<List<IncomingDocument>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(incomingDocumentCrudService.getAll());
    }

    /**
     * Метод получения сущности по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<IncomingDocument> getById(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (RuntimeException e) {
            throw new DocflowRuntimeApplicationException("Введен не валидный UUID");
        }
        Optional<IncomingDocument> incomingDocument = incomingDocumentCrudService.getById(uuid);
        if (incomingDocument.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(incomingDocument.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления сущности
     */
    @PostMapping("/add")
    public ResponseEntity<IncomingDocument> addOrganization(@Valid @RequestBody IncomingDocument incomingDocument) {
        return ResponseEntity.status(HttpStatus.CREATED).body(incomingDocumentCrudService.create(incomingDocument));
    }

    /**
     * Метод сохранения List сущностей
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@Valid @RequestBody IncomingListDTO incomingListDTO) throws BatchUpdateException {
        incomingDocumentCrudService.saveALL(incomingListDTO.getIncomingDocumentList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Входящие документы успешно сохранены"));
    }

    /**
     * Метод обновления сущностей
     */
    @PutMapping("/update")
    public ResponseEntity<IncomingDocument> update(@Valid @RequestBody IncomingDocument incomingDocument) {
        return ResponseEntity.status(HttpStatus.OK).body(incomingDocumentCrudService.update(incomingDocument));
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
        incomingDocumentCrudService.deleteById(uuid);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Входящий документ успешно удалено"));
    }

    /**
     * Метод удаления всех сущностей
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteById() {
        incomingDocumentCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Входящие документы успешно удалены"));
    }
}
