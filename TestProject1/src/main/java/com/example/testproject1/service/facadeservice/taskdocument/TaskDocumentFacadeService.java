package com.example.testproject1.service.facadeservice.taskdocument;

import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.dto.document.TaskDocumentDTO;
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
public class TaskDocumentFacadeService implements CrudFacadeService<TaskDocumentDTO>{
    /**
     * Department сервис
     */
    @Autowired
    private CrudService<TaskDocument> taskDocumentCrudService;
    /**
     * Mapper DTO to Entity, Entity to DTO
     */
    @Autowired
    private MappingUtils mappingUtils;
    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocumentDTO create(TaskDocumentDTO entity) {
        TaskDocument taskDocument = mappingUtils.mapDtoToTaskDocument(entity);
        return mappingUtils.mapTaskDocumentToDto(taskDocumentCrudService.create(taskDocument));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskDocumentDTO> getAll() {
        List<TaskDocument> taskDocumentList = taskDocumentCrudService.getAll();
        return taskDocumentList.stream().map(s -> mappingUtils.mapTaskDocumentToDto(s)).collect(Collectors.toList());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TaskDocumentDTO> getById(UUID id) {
        Optional<TaskDocument> optionalTaskDocument = taskDocumentCrudService.getById(id);
        if (optionalTaskDocument.isPresent()) {
            return Optional.ofNullable(mappingUtils.mapTaskDocumentToDto(optionalTaskDocument.get()));
        }
        return Optional.empty();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocumentDTO update(TaskDocumentDTO entity) {
        TaskDocument taskDocument = mappingUtils.mapDtoToTaskDocument(entity);
        return mappingUtils.mapTaskDocumentToDto(taskDocumentCrudService.update(taskDocument));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<TaskDocumentDTO> entityList) throws BatchUpdateException {
        List<TaskDocument> taskDocumentList = entityList.stream().map(s -> mappingUtils.mapDtoToTaskDocument(s)).collect(Collectors.toList());
        taskDocumentCrudService.saveAll(taskDocumentList);
    }
}
