package com.example.testproject1.controller;

import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.dto.documentdto.IncomingDocumentDTO;
import com.example.testproject1.model.dto.documentdto.IncomingDocumentDtoList;
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
 * Класс контроллер для сущности {@link IncomingDocument}
 *
 * @author smigranov
 */
@RestController
@RequestMapping("/incomingdocuments")
public class IncomingDocumentController {
    @Autowired
    private CrudService<IncomingDocument> incomingDocumentCrudService;
    @Autowired
    private CrudFacadeService<IncomingDocumentDTO> incomingDocumentDTOFacadeService;

    /**
     * Метод получения сущностей IncomingDocument
     */
    @GetMapping
    public ResponseEntity<List<IncomingDocumentDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(incomingDocumentDTOFacadeService.getAll());
    }

    /**
     * Метод получения сущности IncomingDocument по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<IncomingDocumentDTO> getById(@PathVariable UUID id) {
        Optional<IncomingDocumentDTO> incomingDocument = incomingDocumentDTOFacadeService.getById(id);
        if (incomingDocument.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(incomingDocument.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления сущности IncomingDocument
     */
    @PostMapping("/add")
    public ResponseEntity<IncomingDocumentDTO> addIncomingDocument(@Valid @RequestBody IncomingDocumentDTO incomingDocumentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(incomingDocumentDTOFacadeService.create(incomingDocumentDTO));
    }

    /**
     * Метод сохранения List IncomingDocumentDTO
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@Valid @RequestBody IncomingDocumentDtoList incomingDocumentDtoList) {
        incomingDocumentDTOFacadeService.saveAll(incomingDocumentDtoList.getIncomingDocumentList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Входящие документы успешно сохранены"));
    }

    /**
     * Метод обновления сущностей IncomingDocument
     */
    @PutMapping("/update")
    public ResponseEntity<IncomingDocumentDTO> update(@Valid @RequestBody IncomingDocumentDTO incomingDocumentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(incomingDocumentDTOFacadeService.update(incomingDocumentDTO));
    }

    /**
     * Метод удаления сущности IncomingDocument по id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable UUID id) {
        incomingDocumentCrudService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Входящий документ успешно удалено"));
    }

    /**
     * Метод удаления всех IncomingDocument
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteAll() {
        incomingDocumentCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Входящие документы успешно удалены"));
    }
}
