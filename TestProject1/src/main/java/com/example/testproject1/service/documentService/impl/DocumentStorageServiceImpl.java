package com.example.testproject1.service.documentService.impl;

import com.example.testproject1.model.document.BaseDocument;
import com.example.testproject1.service.documentService.DocumentStorageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Класс сохранения объектов BaseDocument созданных с помощью Builder-ов
 *
 * @author smigranov
 */
@Service
public class DocumentStorageServiceImpl implements DocumentStorageService {
    /**
     * Лист для сохранения объектов унаследованных от {@link BaseDocument}
     */
    private List<BaseDocument> documentList = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BaseDocument> getAll() {
        return documentList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAll(BaseDocument baseDocumentList) {
        documentList.add(baseDocumentList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existByRegNumber(Long regNumber) {
        if (documentList.stream().filter(s -> s.getRegNumber() == regNumber).findFirst().isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
