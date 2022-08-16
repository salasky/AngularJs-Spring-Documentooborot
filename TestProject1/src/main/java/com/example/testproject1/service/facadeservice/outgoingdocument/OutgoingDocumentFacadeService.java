package com.example.testproject1.service.facadeservice.outgoingdocument;

import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.dto.document.OutgoingDocumentDTO;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import com.example.testproject1.service.mappingutils.OutgoingDocumentMapperAbsract;
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
public class OutgoingDocumentFacadeService implements CrudFacadeService<OutgoingDocumentDTO> {
    /**
     * OutgoingDocument сервис
     */
    @Autowired
    private CrudService<OutgoingDocument> outgoingDocumentCrudService;
    /**
     * Mapper DTO to Entity, Entity to DTO
     */
    @Autowired
    private OutgoingDocumentMapperAbsract mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public OutgoingDocumentDTO create(OutgoingDocumentDTO entity) {
        OutgoingDocument outgoingDocument = mapper.dtoToOutgoing(entity);
        return mapper.outgoingToDTO(outgoingDocumentCrudService.create(outgoingDocument));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OutgoingDocumentDTO> getAll() {
        List<OutgoingDocument> outgoingDocumentList = outgoingDocumentCrudService.getAll();
        return mapper.listToDtoList(outgoingDocumentList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<OutgoingDocumentDTO> getById(UUID id) {
        Optional<OutgoingDocument> optionalOutgoingDocument = outgoingDocumentCrudService.getById(id);
        if (optionalOutgoingDocument.isPresent()) {
            return Optional.ofNullable(mapper.outgoingToDTO(optionalOutgoingDocument.get()));
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OutgoingDocumentDTO update(OutgoingDocumentDTO entity) {
        OutgoingDocument outgoingDocument = mapper.dtoToOutgoing(entity);
        return mapper.outgoingToDTO(outgoingDocumentCrudService.update(outgoingDocument));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<OutgoingDocumentDTO> entityList) throws BatchUpdateException {
        outgoingDocumentCrudService.saveAll(mapper.dtoListToList(entityList));
    }
}
