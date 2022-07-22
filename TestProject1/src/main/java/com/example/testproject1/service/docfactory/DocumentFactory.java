package com.example.testproject1.service.docfactory;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.service.randomizer.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Абстрактный класс фабрики для {@link TaskDocumentFactory},{@link IncomingDocumentFactory},{@link OutgoingDocumentFactory}
 *
 * @author smigranov
 */
public abstract class DocumentFactory<T extends BaseDocument.BaseDocumentBuilder> implements Factory<BaseDocument> {
    /**
     * Autowired бина {@link Randomizer}
     */
    @Autowired
    protected Randomizer randomizer;

    /**
     * @return Возвращает экземпляр нужно билдера
     */
    public abstract T getBuilder();

    /**
     *  Метод который заполняет особенности конкретной реализации
     * @param builder
     * @return
     */
    public abstract T setFields(T builder);
    /**
     * Собирает готовый документ с полями базового класса
     * @param builder
     * @return
     */
    public BaseDocument createDocument(T builder) {
        return builder
                .setDocId(randomizer.getRandUUID())
                .setDocName(randomizer.getRandDocName())
                .setDocText(randomizer.getRandDocText())
                .setDocRegNumber(randomizer.getRandDocumentRegNumber())
                .setDocDate(randomizer.getRandDocumentData())
                .setDocAuthor(randomizer.getRandDocumentAuthor())
                .build();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BaseDocument create() {
        return createDocument(setFields(getBuilder()));
    }
}
