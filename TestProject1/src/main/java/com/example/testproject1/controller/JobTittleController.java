package com.example.testproject1.controller;

import com.example.testproject1.model.utility.MessageResponseDTO;
import com.example.testproject1.model.dto.staff.JobTittleListDTO;
import com.example.testproject1.model.staff.JobTittle;
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
 * Класс контроллер для сущности {@link JobTittle}
 *
 * @author smigranov
 */
@RestController
@RequestMapping("/jobs")
public class JobTittleController {

    @Autowired
    private CrudService<JobTittle> jobTittleCrudService;

    /**
     * Метод получения JobTittle
     */
    @GetMapping
    public ResponseEntity<List<JobTittle>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(jobTittleCrudService.getAll());
    }

    /**
     * Метод получения JobTittle по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobTittle> getById(@PathVariable UUID id) {
        Optional<JobTittle> jobTittle = jobTittleCrudService.getById(id);
        if (jobTittle.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(jobTittle.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления JobTittle
     */
    @PostMapping("/add")
    public ResponseEntity<JobTittle> addJobTittle(@Valid @RequestBody JobTittle jobTittle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobTittleCrudService.create(jobTittle));
    }

    /**
     * Метод сохранения List JobTittle
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@RequestBody JobTittleListDTO jobTittleListDTO) {
        jobTittleCrudService.saveAll(jobTittleListDTO.getJobList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Должности успешно сохранены"));
    }

    /**
     * Метод обновления JobTittle
     */
    @PutMapping("/update")
    public ResponseEntity<JobTittle> update(@Valid @RequestBody JobTittle jobTittle) {
        return ResponseEntity.status(HttpStatus.OK).body(jobTittleCrudService.update(jobTittle));
    }

    /**
     * Метод удаления JobTittle по id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable UUID id) {
        jobTittleCrudService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Должность успешно удалена"));
    }

    /**
     * Метод удаления всех JobTittle
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteAll() {
        jobTittleCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Должности успешно удалены"));
    }
}
