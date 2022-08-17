package com.example.testproject1.service.facadeservice.organization;

import com.example.testproject1.model.dto.staffdto.OrganizationDTO;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import com.example.testproject1.service.mappingutils.OrganizationMapper;
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
public class OrganizationFacadeService implements CrudFacadeService<OrganizationDTO> {
    @Autowired
    private CrudService<Organization> organizationCrudService;

    @Autowired
    private OrganizationMapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public OrganizationDTO create(OrganizationDTO entity) {
        Organization organization = mapper.dtoToSource(entity);
        return mapper.sourceToDto(organizationCrudService.create(organization));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrganizationDTO> getAll() {
        List<Organization> organizationList = organizationCrudService.getAll();
        return mapper.listToDto(organizationList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<OrganizationDTO> getById(UUID id) {
        return Optional.ofNullable(mapper.sourceToDto(organizationCrudService.getById(id).get()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrganizationDTO update(OrganizationDTO entity) {
        Organization organization = mapper.dtoToSource(entity);
        return mapper.sourceToDto(organizationCrudService.update(organization));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<OrganizationDTO> entityList) {
        organizationCrudService.saveAll(mapper.dtoToList(entityList));
    }
}
