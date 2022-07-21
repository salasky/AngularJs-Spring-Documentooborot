package com.example.testproject1.service.visitorPatternRelase;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.model.OutgoingDocument;
import com.example.testproject1.model.TaskDocument;

/**
 * Интерфейс возврата имени класса
 *
 * @author smigranov
 */
public interface DocumentInspector {
    /**
     * Метод возвращает имя класса TaskDocument
     * @param taskDocument
     * @return
     */
    String visit(TaskDocument taskDocument);

    /**
     * Метод возвращает имя класса IncomingDocument
     * @param incomingDocument
     * @return
     */
    String visit(IncomingDocument incomingDocument);

    /**
     * Метод возвращает имя класса OutgoingDocument
     * @param outgoingDocument
     * @return
     */
    String visit(OutgoingDocument outgoingDocument);

    /**
     * Метод возвращает имя класса BaseDocument
     * @param baseDocument
     * @return
     */
    String visit(BaseDocument baseDocument);
}