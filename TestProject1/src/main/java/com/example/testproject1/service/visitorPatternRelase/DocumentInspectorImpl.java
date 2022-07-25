package com.example.testproject1.service.visitorPatternRelase;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.model.OutgoingDocument;
import com.example.testproject1.model.TaskDocument;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Класс реализации интерфейса {@link DocumentInspector}
 *
 * @author smigranov
 */
@Service
public class DocumentInspectorImpl implements DocumentInspector{
    /**
     * {@inheritDoc}
     */
    @Override
    public String visit(TaskDocument taskDocument) {
        return MessageFormat.format("Поручение {0} от {1}. {2} \n"
                        , taskDocument.getId(), taskDocument.getDocumentData(),taskDocument.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String visit(IncomingDocument incomingDocument) {
        return MessageFormat.format("Входящий документ {0} от {1}. {2} \n"
                , incomingDocument.getId(), incomingDocument.getDocumentData(),incomingDocument.getName());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String visit(OutgoingDocument outgoingDocument) {
        return MessageFormat.format("Исходящий документ {0} от {1}. {2} \n"
                , outgoingDocument.getId(), outgoingDocument.getDocumentData(),outgoingDocument.getName());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String visit(BaseDocument baseDocument) {
        return "";
    }
}
