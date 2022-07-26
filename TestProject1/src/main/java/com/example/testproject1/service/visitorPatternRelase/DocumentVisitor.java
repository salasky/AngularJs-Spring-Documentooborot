package com.example.testproject1.service.visitorPatternRelase;

/**
 * Интерфейс документов для реализации паттерна Посетитель
 *
 * @author smigranov
 */
public interface DocumentVisitor {
    /**
     * Метод вызова нужного метода visit в зависимости от типа объекта
     * @param documentInspector объект класса {@link DocumentInspector}
     * @return объект класса {@link String} неполную информацию о документе для отчета
     */
    String accept(DocumentInspector documentInspector);
}
