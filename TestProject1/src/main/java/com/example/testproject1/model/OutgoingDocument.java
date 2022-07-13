package com.example.testproject1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*@Entity
@Table(name = "OutgoingDocument")*/
@Data
@NoArgsConstructor
public class OutgoingDocument extends BaseDocument{

    private String outgoingDocumentSender;

    private String outgoingDocumentDeliveryType;



}
