package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.documents.TaskDocument;
import org.springframework.stereotype.Service;

/**
 * Класс фабрики для {@link TaskDocument}
 *
 * @author smigranov
 */
@Service
public class TaskDocumentFactory extends DocumentFactory<TaskDocument.TaskBuilder> {

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocument.TaskBuilder getBuilder() {
        return TaskDocument.newBuilder();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDocument.TaskBuilder setFields(TaskDocument.TaskBuilder builder) {
        return builder.setTaskDate(randomizer.getRandTaskOutDate())
                .setTaskExecPeriod(randomizer.getRandTaskExecPeriod())
                .setTaskResponsPerson(randomizer.getRandTaskResponsible())
                .setTaskSignOfControl(randomizer.getTaskSignOfControl())
                .setTaskControlPerson(randomizer.getRandTaskControlPerson());
    }
}
