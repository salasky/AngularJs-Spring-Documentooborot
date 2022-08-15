package com.example.testproject1.controller;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.dto.MessageResponseDTO;
import com.example.testproject1.model.dto.document.IncomingDocumentDTO;
import com.example.testproject1.model.dto.document.OutgoingDocumentDTO;
import com.example.testproject1.model.dto.document.OutgoingListDTO;
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

/**
 * Класс контроллер для сущности {@link com.example.testproject1.model.document.OutgoingDocument}
 *
 * @author smigranov
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/outgoingdocuments")
public class OutgoingDocumentController {
    /**
     * Сервис для работы с OutgoingDocument
     */
    @Autowired
    private CrudService<OutgoingDocument> outgoingDocumentCrudService;
    /**
     * Фасадный Сервис для работы с OutgoingDocument
     */
    @Autowired
    private CrudFacadeService<OutgoingDocumentDTO> outgoingDocumentDTOFacadeService;
    /**
     * Метод получения сущностей
     */
    @GetMapping
    public ResponseEntity<List<OutgoingDocumentDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(outgoingDocumentDTOFacadeService.getAll());
    }

    /**
     * Метод получения сущности по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<OutgoingDocumentDTO> getById(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (RuntimeException e) {
            throw new DocflowRuntimeApplicationException("Введен не валидный UUID");
        }
        Optional<OutgoingDocumentDTO> outgoingDocumentDTO = outgoingDocumentDTOFacadeService.getById(uuid);
        if (outgoingDocumentDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(outgoingDocumentDTO.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления сущности
     */
    @PostMapping("/add")
    public ResponseEntity<OutgoingDocumentDTO> addOrganization(@Valid @RequestBody OutgoingDocumentDTO outgoingDocumentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(outgoingDocumentDTOFacadeService.create(outgoingDocumentDTO));
    }

    /**
     * Метод сохранения List сущностей
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@Valid @RequestBody OutgoingListDTO outgoingListDTO) throws BatchUpdateException {
        outgoingDocumentDTOFacadeService.saveAll(outgoingListDTO.getOutgoingDocuments());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Исходящие документы успешно сохранены"));
    }

    /**
     * Метод обновления сущностей
     */
    @PutMapping("/update")
    public ResponseEntity<OutgoingDocumentDTO> update(@Valid @RequestBody OutgoingDocumentDTO outgoingDocumentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(outgoingDocumentDTOFacadeService.update(outgoingDocumentDTO));
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
        outgoingDocumentCrudService.deleteById(uuid);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Исходящий документ успешно удален"));
    }

    /**
     * Метод удаления всех сущностей
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteById() {
        outgoingDocumentCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Исходящие документы успешно удалены"));
    }
}
