package com.example.testproject1.service.facadeservice.incomingdocument;

import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.dto.document.IncomingDocumentDTO;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.facadeservice.CrudFacadeService;
import com.example.testproject1.service.mappingutils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.BatchUpdateException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Класс реализующий интерфейс {@link CrudFacadeService}. Для выполнения CRUD операций к базе данных.
 *
 * @author smigranov
 */
@Service
public class IncomingDocumentFacadeService implements CrudFacadeService<IncomingDocumentDTO> {
    /**
     * IncomingDocument сервис
     */
    @Autowired
    private CrudService<IncomingDocument> incomingDocumentCrudService;
    /**
     * Mapper DTO to Entity, Entity to DTO
     */
    @Autowired
    private MappingUtils mappingUtils;

    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocumentDTO create(IncomingDocumentDTO entity) {
        IncomingDocument incomingDocument = mappingUtils.mapDtoToIncomingDocument(entity);
        return mappingUtils.mapIncomingDocumentToDto(incomingDocumentCrudService.create(incomingDocument));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IncomingDocumentDTO> getAll() {
        List<IncomingDocument> incomingDocumentList = incomingDocumentCrudService.getAll();
        return incomingDocumentList.stream().map(s -> mappingUtils.mapIncomingDocumentToDto(s)).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<IncomingDocumentDTO> getById(UUID id) {
        Optional<IncomingDocument> optionalIncomingDocument = incomingDocumentCrudService.getById(id);
        if (optionalIncomingDocument.isPresent()) {
            return Optional.ofNullable(mappingUtils.mapIncomingDocumentToDto(optionalIncomingDocument.get()));
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IncomingDocumentDTO update(IncomingDocumentDTO entity) {
        IncomingDocument incomingDocument = mappingUtils.mapDtoToIncomingDocument(entity);
        return mappingUtils.mapIncomingDocumentToDto(incomingDocumentCrudService.update(incomingDocument));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<IncomingDocumentDTO> entityList) throws BatchUpdateException {
        List<IncomingDocument> incomingDocumentList = entityList.stream()
                .map(s -> mappingUtils.mapDtoToIncomingDocument(s)).collect(Collectors.toList());
        incomingDocumentCrudService.saveAll(incomingDocumentList);
    }
}
