package com.example.testproject1.service.facadeservice.department;

import com.example.testproject1.model.dto.staff.DepartmentDTO;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import com.example.testproject1.service.mappingutils.DepartmentMapperAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.BatchUpdateException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс реализующий интерфейс {@link CrudFacadeService}. Для выполнения CRUD операций к базе данных.
 *
 * @author smigranov
 */
@Service
public class DepartmentFacadeService implements CrudFacadeService<DepartmentDTO> {
    /**
     * Department сервис
     */
    @Autowired
    private CrudService<Department> departmentCrudService;
    /**
     * Mapper DTO to Entity, Entity to DTO
     */
    @Autowired
    private DepartmentMapperAbstract mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public DepartmentDTO create(DepartmentDTO entity) {
        Department department = mapper.dtoToDepartment(entity);
        return mapper.departmentToDTO(departmentCrudService.create(department));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DepartmentDTO> getAll() {
        List<Department> departmentList = departmentCrudService.getAll();
        return mapper.listToDtoList(departmentList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<DepartmentDTO> getById(UUID id) {
        Optional<Department> optionalDepartment = departmentCrudService.getById(id);
        if (optionalDepartment.isPresent()) {
            return Optional.ofNullable(mapper.departmentToDTO(optionalDepartment.get()));
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DepartmentDTO update(DepartmentDTO entity) {
        Department department = mapper.dtoToDepartment(entity);
        return mapper.departmentToDTO(departmentCrudService.update(department));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<DepartmentDTO> entityList) throws BatchUpdateException {
        departmentCrudService.saveAll(mapper.dtoListToList(entityList));
    }
}
