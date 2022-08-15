package com.example.testproject1.service.mappingutils;

import com.example.testproject1.model.dto.DepartmentDTO;
import com.example.testproject1.model.staff.Department;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    //Department из entity в dto
    public DepartmentDTO mapToDepartmentDto(Department department){
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setFullName(department.getFullName());
        dto.setShortName(department.getShortName());
        dto.setSupervisor(department.getSupervisor());
        dto.setContactNumber(department.getContactNumber());
        dto.setOrganizationiD(department.getOrganization().getId());
        return dto;
    }
}
