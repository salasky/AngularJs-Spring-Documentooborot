package com.example.testproject1.service.mappingdto;

import com.example.testproject1.model.document.IncomingDocument;
import com.example.testproject1.model.dto.documentdto.IncomingDocumentDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public abstract class IncomingDocumentMapper {

    @Mapping(target = "authorId", source = "incomingDocument.author.id")
    @Mapping(target = "senderId", source = "incomingDocument.sender.id")
    @Mapping(target = "destinationId", source = "incomingDocument.destination.id")
    public abstract IncomingDocumentDTO incomingToDTO(IncomingDocument incomingDocument);

    @Mapping(target = "author.id", source = "incomingDocumentDTO.authorId")
    @Mapping(target = "sender.id", source = "incomingDocumentDTO.senderId")
    @Mapping(target = "destination.id", source = "incomingDocumentDTO.destinationId")
    public abstract IncomingDocument dtoToIncoming(IncomingDocumentDTO incomingDocumentDTO);

    public abstract List<IncomingDocumentDTO> listToDtoList(List<IncomingDocument> taskDocumentList);

    public abstract List<IncomingDocument> dtoListToList(List<IncomingDocumentDTO> taskDocumentList);
}
