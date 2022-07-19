package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.TaskDocument;
import org.springframework.stereotype.Service;

/**
 * Класс фабрики для {@link com.example.testproject1.model.TaskDocument}
 *
 * @author smigranov
 */
@Service
public class TaskDocumentFactory extends DocumentFactory<TaskDocument.TaskBuilder> {
    /**
     * {@inheritDoc}
     *
     * @return Возвращает объект класса BaseDocument
     */
    @Override
    public BaseDocument create() {
        return createBaseDocument(TaskDocument.newBuilder()
                .setTaskDate(randomizer.getRandTaskOutDate())
                .setTaskExecPeriod(randomizer.getRandTaskExecPeriod())
                .setTaskResponsPerson(randomizer.getRandTaskResponsible())
                .setTaskSignOfControl(randomizer.getTaskSignOfControl())
                .setTaskControlPerson(randomizer.getRandTaskControlPerson()));
    }
}
