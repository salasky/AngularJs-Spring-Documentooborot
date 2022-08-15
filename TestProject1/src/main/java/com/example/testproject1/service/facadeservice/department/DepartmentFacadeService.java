package com.example.testproject1.service.facadeservice.department;

import com.example.testproject1.model.dto.DepartmentDTO;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import com.example.testproject1.service.mappingutils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Класс реализующий интерфейс {@link CrudFacadeService}. Для выполнения CRUD операций к базе данных.
 *
 * @author smigranov
 */
@Service
public class DepartmentFacadeService implements CrudFacadeService<DepartmentDTO,Department> {
    /**
     * Department сервис
     */
    @Autowired
    private CrudService<Department> departmentCrudService;
    /**
     * Mapper DTO to Entity, Entity to DTO
     */
    @Autowired
    private MappingUtils mappingUtils;

    /**
     * {@inheritDoc}
     */
    @Override
    public DepartmentDTO create(Department entity) {
        return mappingUtils.mapDepartmentToDto(departmentCrudService.create(entity));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DepartmentDTO> getAll() {
        List<Department> departmentList=departmentCrudService.getAll();
        return departmentList.stream().map(s->mappingUtils.mapDepartmentToDto(s)).collect(Collectors.toList());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<DepartmentDTO> getById(UUID id) {
        Optional<Department> optionalDepartment=departmentCrudService.getById(id);
        if(optionalDepartment.isPresent()){
            return Optional.ofNullable(mappingUtils.mapDepartmentToDto(optionalDepartment.get()));
        }
        return Optional.empty();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DepartmentDTO update(Department entity) {
        return mappingUtils.mapDepartmentToDto(departmentCrudService.update(entity));
    }
}
