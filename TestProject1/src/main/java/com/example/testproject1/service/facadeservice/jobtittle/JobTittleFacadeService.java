package com.example.testproject1.service.facadeservice.jobtittle;

import com.example.testproject1.model.dto.staffdto.JobTittleDTO;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import com.example.testproject1.service.mappingutils.JobTittleMapper;
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
public class JobTittleFacadeService implements CrudFacadeService<JobTittleDTO> {
    @Autowired
    private CrudService<JobTittle> jobTittleCrudService;

    @Autowired
    private JobTittleMapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public JobTittleDTO create(JobTittleDTO entity) {
        JobTittle jobTittle = mapper.dtoToSource(entity);
        return mapper.sourceToDto(jobTittleCrudService.create(jobTittle));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobTittleDTO> getAll() {
        List<JobTittle> jobTittleList = jobTittleCrudService.getAll();
        return mapper.listToDto(jobTittleList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<JobTittleDTO> getById(UUID id) {
        return Optional.ofNullable(mapper.sourceToDto(jobTittleCrudService.getById(id).get()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobTittleDTO update(JobTittleDTO entity) {
        JobTittle jobTittle = mapper.dtoToSource(entity);
        return mapper.sourceToDto(jobTittleCrudService.update(jobTittle));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<JobTittleDTO> entityList) {
        jobTittleCrudService.saveAll(mapper.dtoToList(entityList));
    }
}
