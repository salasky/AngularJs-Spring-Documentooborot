package com.example.testproject1.service.DocBuilder;

import com.example.testproject1.exeption.DocumentExistsException;
import com.example.testproject1.model.TaskDocument;
import org.springframework.stereotype.Component;

public interface TaskBuilder {


    public TaskBuilder fixDocumentName();

    public TaskBuilder fixDocumentText();

    public TaskBuilder fixDocumentRegNumber() throws DocumentExistsException;

    public TaskBuilder fixDocumentData();

    public TaskBuilder fixDocumentAuthor();

    public TaskBuilder fixTaskOutDate();

    public TaskBuilder fixTaskExecPeriod();

    public TaskBuilder fixTaskResponsible();

    public TaskBuilder fixTaskSignOfControl();

    public TaskBuilder fixTaskControlPerson();

    public TaskDocument build();
}
