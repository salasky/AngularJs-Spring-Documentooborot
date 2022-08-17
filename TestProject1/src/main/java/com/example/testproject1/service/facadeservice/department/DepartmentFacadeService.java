package com.example.testproject1.service.facadeservice.department;

import com.example.testproject1.model.dto.staffdto.DepartmentDTO;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import com.example.testproject1.service.mappingutils.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private CrudService<Department> departmentCrudService;

    @Autowired
    private DepartmentMapper mapper;

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
        return Optional.ofNullable(mapper.departmentToDTO(departmentCrudService.getById(id).get()));
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
    public void saveAll(List<DepartmentDTO> entityList) {
        departmentCrudService.saveAll(mapper.dtoListToList(entityList));
    }
}
