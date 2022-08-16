package com.example.testproject1.service.facadeservice.outgoingdocument;

import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.dto.document.OutgoingDocumentDTO;
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
    private MappingUtils mappingUtils;

    /**
     * {@inheritDoc}
     */
    @Override
    public OutgoingDocumentDTO create(OutgoingDocumentDTO entity) {
        OutgoingDocument outgoingDocument = mappingUtils.mapDtoToOutgoingDocument(entity);
        return mappingUtils.mapOutgoingDocumentToDto(outgoingDocumentCrudService.create(outgoingDocument));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OutgoingDocumentDTO> getAll() {
        List<OutgoingDocument> outgoingDocumentList = outgoingDocumentCrudService.getAll();
        return outgoingDocumentList.stream().map(s -> mappingUtils.mapOutgoingDocumentToDto(s)).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<OutgoingDocumentDTO> getById(UUID id) {
        Optional<OutgoingDocument> optionalOutgoingDocument = outgoingDocumentCrudService.getById(id);
        if (optionalOutgoingDocument.isPresent()) {
            return Optional.ofNullable(mappingUtils.mapOutgoingDocumentToDto(optionalOutgoingDocument.get()));
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OutgoingDocumentDTO update(OutgoingDocumentDTO entity) {
        OutgoingDocument outgoingDocument = mappingUtils.mapDtoToOutgoingDocument(entity);
        return mappingUtils.mapOutgoingDocumentToDto(outgoingDocumentCrudService.update(outgoingDocument));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<OutgoingDocumentDTO> entityList) throws BatchUpdateException {
        List<OutgoingDocument> outgoingDocumentList = entityList.stream()
                .map(s -> mappingUtils.mapDtoToOutgoingDocument(s)).collect(Collectors.toList());
        outgoingDocumentCrudService.saveAll(outgoingDocumentList);
    }
}
