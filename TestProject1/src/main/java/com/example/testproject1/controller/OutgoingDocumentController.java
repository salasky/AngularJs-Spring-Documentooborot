package com.example.testproject1.controller;

import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.dto.documentdto.OutgoingDocumentDTO;
import com.example.testproject1.model.dto.documentdto.OutgoingDocumentDtoListForMapping;
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
 * Класс контроллер для сущности {@link com.example.testproject1.model.document.OutgoingDocument}
 *
 * @author smigranov
 */
@RestController
@RequestMapping("/outgoingdocuments")
public class OutgoingDocumentController {

    @Autowired
    private CrudService<OutgoingDocument> outgoingDocumentCrudService;
    @Autowired
    private CrudFacadeService<OutgoingDocumentDTO> outgoingDocumentDTOFacadeService;

    /**
     * Метод получения OutgoingDocuments
     */
    @GetMapping
    public ResponseEntity<List<OutgoingDocumentDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(outgoingDocumentDTOFacadeService.getAll());
    }

    /**
     * Метод получения OutgoingDocument по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<OutgoingDocumentDTO> getById(@PathVariable UUID id) {
        Optional<OutgoingDocumentDTO> outgoingDocumentDTO = outgoingDocumentDTOFacadeService.getById(id);
        if (outgoingDocumentDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(outgoingDocumentDTO.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления OutgoingDocument
     */
    @PostMapping("/add")
    public ResponseEntity<OutgoingDocumentDTO> addOutgoingDocument(@Valid @RequestBody OutgoingDocumentDTO outgoingDocumentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(outgoingDocumentDTOFacadeService.create(outgoingDocumentDTO));
    }

    /**
     * Метод сохранения List OutgoingDocument
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@Valid @RequestBody OutgoingDocumentDtoListForMapping outgoingDocumentDtoListForMapping) {
        outgoingDocumentDTOFacadeService.saveAll(outgoingDocumentDtoListForMapping.getOutgoingDocuments());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Исходящие документы успешно сохранены"));
    }

    /**
     * Метод обновления OutgoingDocument
     */
    @PutMapping("/update")
    public ResponseEntity<OutgoingDocumentDTO> update(@Valid @RequestBody OutgoingDocumentDTO outgoingDocumentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(outgoingDocumentDTOFacadeService.update(outgoingDocumentDTO));
    }

    /**
     * Метод удаления OutgoingDocument по id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable UUID id) {
        outgoingDocumentCrudService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Исходящий документ успешно удален"));
    }

    /**
     * Метод удаления всех OutgoingDocument
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteAll() {
        outgoingDocumentCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Исходящие документы успешно удалены"));
    }
}
