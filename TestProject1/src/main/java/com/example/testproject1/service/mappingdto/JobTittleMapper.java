package com.example.testproject1.service.mappingdto;

import com.example.testproject1.model.dto.staffdto.JobTittleDTO;
import com.example.testproject1.model.staff.JobTittle;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Интенрфейс маппера для сущности {@link JobTittle}
 *
 * @author smigranov
 */
@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface JobTittleMapper {

    JobTittleDTO sourceToDto(JobTittle source);

    JobTittle dtoToSource(JobTittleDTO destination);

    List<JobTittleDTO> listToDto(List<JobTittle> transactions);

    List<JobTittle> dtoToList(List<JobTittleDTO> transactions);
}
