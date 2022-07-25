package com.example.testproject1.service.visitorPatternRelase;

/**
 * Интерфейс документов для реализации паттерна Посетитель
 *
 * @author smigranov
 */
public interface DocumentVisitor {
    /**
     * Метод вызова нужного метода visit в зависимости от типа объекта
     * @param documentInspector
     * @return
     */
    String accept(DocumentInspector documentInspector);
}
