package com.example.testproject1.controller;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.dto.MessageResponseDTO;
import com.example.testproject1.model.dto.document.TaskListDTO;
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
@RequestMapping("/taskdocuments")
public class TaskDocumentController {
    /**
     * Сервис для работы с taskdocument
     */
    @Autowired
    private CrudService<TaskDocument> taskDocumentCrudService;

    /**
     * Метод получения сущностей
     */
    @GetMapping
    public ResponseEntity<List<TaskDocument>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(taskDocumentCrudService.getAll());
    }

    /**
     * Метод получения сущности по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDocument> getById(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (RuntimeException e) {
            throw new DocflowRuntimeApplicationException("Введен не валидный UUID");
        }
        Optional<TaskDocument> taskDocument = taskDocumentCrudService.getById(uuid);
        if (taskDocument.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(taskDocument.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления сущности
     */
    @PostMapping("/add")
    public ResponseEntity<TaskDocument> addOrganization(@Valid @RequestBody TaskDocument taskDocument) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDocumentCrudService.create(taskDocument));
    }

    /**
     * Метод сохранения List сущностей
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@Valid @RequestBody TaskListDTO taskListDTO) throws BatchUpdateException {
        taskDocumentCrudService.saveALL(taskListDTO.getTaskDocumentList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Task успешно сохранен"));
    }

    /**
     * Метод обновления сущностей
     */
    @PutMapping("/update")
    public ResponseEntity<TaskDocument> update(@Valid @RequestBody TaskDocument taskDocument) {
        return ResponseEntity.status(HttpStatus.OK).body(taskDocumentCrudService.update(taskDocument));
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
        taskDocumentCrudService.deleteById(uuid);
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
