package com.example.testproject1.controller;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.dto.staff.JobTittleListDTO;
import com.example.testproject1.model.dto.MessageResponseDTO;
import com.example.testproject1.model.staff.JobTittle;
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

/**
 * Класс контроллер для сущности {@link JobTittle}
 *
 * @author smigranov
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/jobs")
public class JobTittleController {
    /**
     * Сервис для работы с jobTittle
     */
    @Autowired
    private CrudService<JobTittle> jobTittleCrudService;

    /**
     * Метод получения сущностей
     */
    @GetMapping
    public ResponseEntity<List<JobTittle>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(jobTittleCrudService.getAll());
    }

    /**
     * Метод получения сущности по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobTittle> getById(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (RuntimeException e) {
            throw new DocflowRuntimeApplicationException("Введен не валидный UUID");
        }
        Optional<JobTittle> jobTittle = jobTittleCrudService.getById(uuid);
        if (jobTittle.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(jobTittle.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления сущности
     */
    @PostMapping("/add")
    public ResponseEntity<JobTittle> addOrganization(@Valid @RequestBody JobTittle jobTittle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobTittleCrudService.create(jobTittle));
    }

    /**
     * Метод сохранения List сущностей
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@RequestBody JobTittleListDTO jobTittleListDTO) throws BatchUpdateException {
        jobTittleCrudService.saveALL(jobTittleListDTO.getJobList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Должноасти успешно сохранены"));
    }

    /**
     * Метод обновления сущностей
     */
    @PutMapping("/update")
    public ResponseEntity<JobTittle> update(@Valid @RequestBody JobTittle jobTittle) {
        return ResponseEntity.status(HttpStatus.OK).body(jobTittleCrudService.update(jobTittle));
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
        jobTittleCrudService.deleteById(uuid);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Должноасть успешно удалена"));
    }

    /**
     * Метод удаления всех сущностей
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteById() {
        jobTittleCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Должноасти успешно удалены"));
    }
}
