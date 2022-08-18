package com.example.testproject1.service.facadeservice.incomingdocument;

import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.dto.documentdto.IncomingDocumentDTO;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import com.example.testproject1.service.mappingdto.IncomingDocumentMapper;
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
public class IncomingDocumentFacadeService implements CrudFacadeService<IncomingDocumentDTO> {

    @Autowired
    private CrudService<IncomingDocument> incomingDocumentCrudService;

    @Autowired
    private IncomingDocumentMapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocumentDTO create(IncomingDocumentDTO entity) {
        IncomingDocument incomingDocument = mapper.dtoToIncoming(entity);
        return mapper.incomingToDTO(incomingDocumentCrudService.create(incomingDocument));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IncomingDocumentDTO> getAll() {
        List<IncomingDocument> incomingDocumentList = incomingDocumentCrudService.getAll();
        return mapper.listToDtoList(incomingDocumentList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<IncomingDocumentDTO> getById(UUID id) {
        return Optional.ofNullable(mapper.incomingToDTO(incomingDocumentCrudService.getById(id).get()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocumentDTO update(IncomingDocumentDTO entity) {
        IncomingDocument incomingDocument = mapper.dtoToIncoming(entity);
        return mapper.incomingToDTO(incomingDocumentCrudService.update(incomingDocument));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<IncomingDocumentDTO> entityList) {
        incomingDocumentCrudService.saveAll(mapper.dtoListToList(entityList));
    }
}
