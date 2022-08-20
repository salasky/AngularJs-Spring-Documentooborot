package com.example.testproject1.controller;

import com.example.testproject1.model.dto.staffdto.JobTittleDTO;
import com.example.testproject1.model.dto.staffdto.JobTittleDtoList;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.utility.MessageResponseDTO;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс контроллер для сущности {@link JobTittle}
 *
 * @author smigranov
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jobs")
public class JobTittleController {

    @Autowired
    private CrudService<JobTittle> jobTittleCrudService;

    @Autowired
    private CrudFacadeService<JobTittleDTO> jobTittleCrudFacadeService;

    /**
     * Метод получения JobTittle
     */
    @GetMapping
    public ResponseEntity<List<JobTittleDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(jobTittleCrudFacadeService.getAll());
    }

    /**
     * Метод получения JobTittle по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobTittleDTO> getById(@PathVariable UUID id) {
        Optional<JobTittleDTO> jobTittleDTO = jobTittleCrudFacadeService.getById(id);
        if (jobTittleDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(jobTittleDTO.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления JobTittle
     */
    @PostMapping("/add")
    public ResponseEntity<JobTittleDTO> addJobTittle(@Valid @RequestBody JobTittleDTO jobTittleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobTittleCrudFacadeService.create(jobTittleDTO));
    }

    /**
     * Метод сохранения List JobTittle
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@RequestBody JobTittleDtoList jobTittleDtoList) {
        jobTittleCrudFacadeService.saveAll(jobTittleDtoList.getJobList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("JobTittles успешно сохранены"));
    }

    /**
     * Метод обновления JobTittle
     */
    @PutMapping("/update")
    public ResponseEntity<JobTittleDTO> update(@Valid @RequestBody JobTittleDTO jobTittleDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(jobTittleCrudFacadeService.update(jobTittleDTO));
    }

    /**
     * Метод удаления JobTittle по id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable UUID id) {
        jobTittleCrudService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("JobTittle успешно удалена"));
    }

    /**
     * Метод удаления всех JobTittle
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteAll() {
        jobTittleCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("JobTittle успешно удалены"));
    }
}
