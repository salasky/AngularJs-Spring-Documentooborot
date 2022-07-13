package com.example.testproject1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*@Entity
@Table(name = "IncomingDocument")*/
@Data
@NoArgsConstructor
public class IncomingDocument extends BaseDocument{

    private String incomingDocumentSender;

    private String incomingDocumentDestination;

    private Long incomingDocumentNumber;

    private String incomingDocumentDate;


}
