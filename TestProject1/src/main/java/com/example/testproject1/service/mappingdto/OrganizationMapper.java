package com.example.testproject1.service.mappingdto;

import com.example.testproject1.model.dto.staffdto.OrganizationDTO;
import com.example.testproject1.model.staff.Organization;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Интерфейс маппера для сущности {@link com.example.testproject1.model.staff.Organization}
 *
 * @author smigranov
 */
@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface OrganizationMapper {

    OrganizationDTO sourceToDto(Organization source);
    Organization dtoToSource(OrganizationDTO destination);

    List<OrganizationDTO> listToDto(List<Organization> transactions);
    List<Organization> dtoToList(List<OrganizationDTO> transactions);
}
