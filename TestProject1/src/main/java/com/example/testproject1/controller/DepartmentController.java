package com.example.testproject1.controller;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.dto.DepartmentListDTO;
import com.example.testproject1.model.dto.JobTittleListDTO;
import com.example.testproject1.model.dto.MessageResponseDTO;
import com.example.testproject1.model.staff.Department;
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
 * Класс контроллер для сущности {@link com.example.testproject1.model.staff.Department}
 *
 * @author smigranov
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    /**
     * Сервис для работы с jobTittle
     */
    @Autowired
    private CrudService<Department> departmentCrudService;

    /**
     * Метод получения сущностей
     */
    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(departmentCrudService.getAll());
    }

    /**
     * Метод получения сущности по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (RuntimeException e) {
            throw new DocflowRuntimeApplicationException("Введен не валидный UUID");
        }
        Optional<Department> department = departmentCrudService.getById(uuid);
        if (department.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(department.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления сущности
     */
    @PostMapping("/add")
    public ResponseEntity<Department> addOrganization(@Valid @RequestBody Department department) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentCrudService.create(department));
    }

    /**
     * Метод сохранения List сущностей
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@Valid @RequestBody DepartmentListDTO departmentListDTO) throws BatchUpdateException {
        departmentCrudService.saveALL(departmentListDTO.getDepartmentList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Подразделения успешно сохранены"));
    }

    /**
     * Метод обновления сущностей
     */
    @PutMapping("/update")
    public ResponseEntity<Department> update(@Valid @RequestBody Department department) {
        return ResponseEntity.status(HttpStatus.OK).body(departmentCrudService.update(department));
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
        departmentCrudService.deleteById(uuid);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Подразделение успешно удалено"));
    }

    /**
     * Метод удаления всех сущностей
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteById() {
        departmentCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Подразделения успешно удалены"));
    }
}
