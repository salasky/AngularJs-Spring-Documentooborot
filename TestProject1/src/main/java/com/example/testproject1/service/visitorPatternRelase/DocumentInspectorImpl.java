package com.example.testproject1.service.visitorPatternRelase;

import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.IncomingDocument;
import com.example.testproject1.model.OutgoingDocument;
import com.example.testproject1.model.TaskDocument;
import org.springframework.stereotype.Service;

/**
 * Класс реализации интерфейса {@link DocumentInspector}
 *
 * @author smigranov
 */
@Service
public class DocumentInspectorImpl implements DocumentInspector{
    @Override
    public String visit(TaskDocument taskDocument) {
        return "Поручение";
    }

    @Override
    public String visit(IncomingDocument incomingDocument) {
        return "Входящий документ";
    }

    @Override
    public String visit(OutgoingDocument outgoingDocument) {
        return "Исходящий документ";
    }

    @Override
    public String visit(BaseDocument baseDocument) {
        return "";
    }
}
