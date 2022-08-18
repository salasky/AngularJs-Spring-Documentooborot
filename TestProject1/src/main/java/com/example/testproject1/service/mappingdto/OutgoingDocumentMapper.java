package com.example.testproject1.service.mappingdto;

import com.example.testproject1.model.document.OutgoingDocument;
import com.example.testproject1.model.dto.documentdto.OutgoingDocumentDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public abstract class OutgoingDocumentMapper {

    @Mapping(target = "authorId", source = "outgoingDocument.author.id")
    @Mapping(target = "senderId", source = "outgoingDocument.sender.id")
    public abstract OutgoingDocumentDTO outgoingToDTO(OutgoingDocument outgoingDocument);

    @Mapping(target = "author.id", source = "outgoingDocumentDTO.authorId")
    @Mapping(target = "sender.id", source = "outgoingDocumentDTO.senderId")
    public abstract OutgoingDocument dtoToOutgoing(OutgoingDocumentDTO outgoingDocumentDTO);

    public abstract List<OutgoingDocumentDTO> listToDtoList(List<OutgoingDocument> taskDocumentList);

    public abstract List<OutgoingDocument> dtoListToList(List<OutgoingDocumentDTO> taskDocumentList);
}
