package com.example.testproject1.controller;

import com.example.testproject1.model.dto.staffdto.DepartmentDTO;
import com.example.testproject1.model.dto.staffdto.DepartmentDtoList;
import com.example.testproject1.model.staff.Department;
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
 * Класс контроллер для сущности {@link com.example.testproject1.model.staff.Department}
 *
 * @author smigranov
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private CrudService<Department> departmentCrudService;
    @Autowired
    private CrudFacadeService<DepartmentDTO> departmentDTOCrudFacadeService;

    /**
     * Метод получения сущностей Department
     */
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(departmentDTOCrudFacadeService.getAll());
    }

    /**
     * Метод получения сущности Department по id
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getById(@PathVariable UUID id) {
        Optional<DepartmentDTO> departmentDTO = departmentDTOCrudFacadeService.getById(id);
        if (departmentDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(departmentDTO.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Метод добавления Department
     */
    @PostMapping("/add")
    public ResponseEntity<DepartmentDTO> addDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentDTOCrudFacadeService.create(departmentDTO));
    }

    /**
     * Метод сохранения List Department
     */
    @PostMapping("/saveAll")
    public ResponseEntity<MessageResponseDTO> saveAll(@Valid @RequestBody DepartmentDtoList departmentDtoList) {
        departmentDTOCrudFacadeService.saveAll(departmentDtoList.getDepartmentList());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("Подразделения успешно сохранены"));
    }

    /**
     * Метод обновления Department
     */
    @PutMapping("/update")
    public ResponseEntity<DepartmentDTO> update(@Valid @RequestBody DepartmentDTO departmentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(departmentDTOCrudFacadeService.update(departmentDTO));
    }

    /**
     * Метод удаления Department по id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable UUID id) {
        departmentCrudService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Подразделение успешно удалено"));
    }

    /**
     * Метод удаления всех Department
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<MessageResponseDTO> deleteAll() {
        departmentCrudService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Подразделения успешно удалены"));
    }
}
