package com.example.testproject1.service.mappingdto;

import com.example.testproject1.model.document.TaskDocument;
import com.example.testproject1.model.dto.documentdto.TaskDocumentDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public abstract class TaskDocumentMapper {

    @Mapping(target = "authorId", source = "taskDocument.author.id")
    @Mapping(target = "responsibleId", source = "taskDocument.responsible.id")
    @Mapping(target = "controlPersonId", source = "taskDocument.controlPerson.id")
    public abstract TaskDocumentDTO taskToDTO(TaskDocument taskDocument);

    @Mapping(target = "author.id", source = "taskDocumentDTO.authorId")
    @Mapping(target = "responsible.id", source = "taskDocumentDTO.responsibleId")
    @Mapping(target = "controlPerson.id", source = "taskDocumentDTO.controlPersonId")
    public abstract TaskDocument dtoToTask(TaskDocumentDTO taskDocumentDTO);

    public abstract List<TaskDocumentDTO> listToDtoList(List<TaskDocument> taskDocumentList);

    public abstract List<TaskDocument> dtoListToList(List<TaskDocumentDTO> taskDocumentList);
}
