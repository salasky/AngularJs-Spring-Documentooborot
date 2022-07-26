package com.example.testproject1.service.visitorPatternRelase;

import com.example.testproject1.model.documents.BaseDocument;
import com.example.testproject1.model.documents.IncomingDocument;
import com.example.testproject1.model.documents.OutgoingDocument;
import com.example.testproject1.model.documents.TaskDocument;
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
                        , taskDocument.getId(), taskDocument.getCreatingDate(),taskDocument.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String visit(IncomingDocument incomingDocument) {
        return MessageFormat.format("Входящий документ {0} от {1}. {2} \n"
                , incomingDocument.getId(), incomingDocument.getCreatingDate(),incomingDocument.getName());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String visit(OutgoingDocument outgoingDocument) {
        return MessageFormat.format("Исходящий документ {0} от {1}. {2} \n"
                , outgoingDocument.getId(), outgoingDocument.getCreatingDate(),outgoingDocument.getName());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String visit(BaseDocument baseDocument) {
        return "";
    }
}
