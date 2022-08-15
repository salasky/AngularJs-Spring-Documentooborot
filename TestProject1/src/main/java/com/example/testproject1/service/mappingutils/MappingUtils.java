package com.example.testproject1.service.mappingutils;

import com.example.testproject1.model.dto.DepartmentDTO;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    //Department из entity в dto
    public DepartmentDTO mapDepartmentToDto(Department department){
       return DepartmentDTO.newBuilder()
                .setId(department.getId())
                .setFullName(department.getFullName())
                .setShortName(department.getShortName())
                .setSupervisor(department.getSupervisor())
                .setContactNumber(department.getContactNumber())
                .setOrganization(department.getOrganization()).build();
    }
    //Department из dto в entity
    public Department mapDtoToDepartment(DepartmentDTO departmentDTO){
        return Department.newBuilder()
                .setId(departmentDTO.getId())
                .setFullName(departmentDTO.getFullName())
                .setShortName(departmentDTO.getShortName())
                .setSupervisor(departmentDTO.getSupervisor())
                .setContactNumber(departmentDTO.getContactNumber())
                .setOrganization(Organization.newBuilder().setId(departmentDTO.getId()).build()).build();
    }
}
