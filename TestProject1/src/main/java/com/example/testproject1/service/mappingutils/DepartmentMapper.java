package com.example.testproject1.service.mappingutils;

import com.example.testproject1.model.dto.staffdto.DepartmentDTO;
import com.example.testproject1.model.staff.Department;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Интерфейс маппера для сущности {@link Department}
 *
 * @author smigranov
 */
@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public abstract class DepartmentMapper {

    @Mapping(target = "organizationId", source = "department.organization.id")
    public abstract DepartmentDTO departmentToDTO(Department department);

    @Mapping(target = "organization.id", source = "departmentDTO.organizationId")
    public abstract Department dtoToDepartment(DepartmentDTO departmentDTO);

    public abstract List<DepartmentDTO> listToDtoList(List<Department> taskDocumentList);

    public abstract List<Department> dtoListToList(List<DepartmentDTO> taskDocumentList);
}
