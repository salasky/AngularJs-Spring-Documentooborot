package com.example.testproject1.service.visitorPatternRelase;

import com.example.testproject1.model.documents.BaseDocument;
import com.example.testproject1.model.documents.IncomingDocument;
import com.example.testproject1.model.documents.OutgoingDocument;
import com.example.testproject1.model.documents.TaskDocument;

/**
 * Интерфейс возврата имени класса
 *
 * @author smigranov
 */
public interface DocumentInspector {
    /**
     * Метод возвращает информацию о экземпляре класса TaskDocument для формирования отчета
     * @param taskDocument
     * @return объект класса {@link String} неполную информацию о документе для отчета
     */
    String visit(TaskDocument taskDocument);

    /**
     * Метод возвращает информацию о экземпляре класса IncomingDocument для формирования отчета
     * @param incomingDocument
     * @return объект класса {@link String} неполную информацию о документе для отчета
     */
    String visit(IncomingDocument incomingDocument);

    /**
     * Метод возвращает информацию о экземпляре класса OutgoingDocument для формирования отчета
     * @param outgoingDocument
     * @return объект класса {@link String} неполную информацию о документе для отчета
     */
    String visit(OutgoingDocument outgoingDocument);

    /**
     * Метод возвращает имя класса BaseDocument
     * @param baseDocument
     * @return объект класса {@link String} неполную информацию о документе для отчета
     */
    String visit(BaseDocument baseDocument);
}
