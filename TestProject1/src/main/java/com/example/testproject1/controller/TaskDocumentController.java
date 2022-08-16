package com.example.testproject1.controller;

import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.dto.MessageResponseDTO;
import com.example.testproject1.model.dto.document.TaskDocumentDTO;
import com.example.testproject1.model.dto.document.TaskListDTO;
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
@RequestMapping("/taskdocuments")
public class TaskDocumentController {
    /**
     * Сервис для работы с taskdocument
     */
    @Autowired
    private CrudService<TaskDocument> taskDocumentCrudService;
    /**
     * Фасадный Сервис для работы с TaskDocument
     */
    @Autowired
    private CrudFacadeService<TaskDocumentDTO> taskDocumentDTOFacadeService;

    /**
     * Метод получения сущностей
     */
    @GetMapping
    public ResponseEntity<List<TaskDocumentDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(taskDocumentDTOFacadeService.getAll());
    }

    /**
     * Метод получения сущности по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDocumentDTO> getById(@PathVariable UUID id) {
        Optional<TaskDocumentDTO> taskDocumentDTO = taskDocumentDTOFacadeService.getById(id);
        if (taskDocumentDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(taskDocumentDTO.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления сущности
     */
    @PostMapping("/add")
    public ResponseEntity<TaskDocumentDTO> addOrganization(@Valid @RequestBody TaskDocumentDTO taskDocument) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDocumentDTOFacadeService.create(taskDocument));
    }

    /**
     * Метод сохранения List сущностей
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@Valid @RequestBody TaskListDTO taskListDTO) throws BatchUpdateException {
        taskDocumentDTOFacadeService.saveAll(taskListDTO.getTaskDocumentList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Поручения успешно сохранены"));
    }

    /**
     * Метод обновления сущностей
     */
    @PutMapping("/update")
    public ResponseEntity<TaskDocumentDTO> update(@Valid @RequestBody TaskDocumentDTO taskDocumentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(taskDocumentDTOFacadeService.update(taskDocumentDTO));
    }

    /**
     * Метод удаления сущности по id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable UUID id) {
        taskDocumentCrudService.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("TaskDocument успешно удален"));
    }

    /**
     * Метод удаления всех сущностей
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteById() {
        taskDocumentCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("TaskDocuments успешно удалены"));
    }
}
