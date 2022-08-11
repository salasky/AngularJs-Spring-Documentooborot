package com.example.testproject1.service.importxmltodatabase.Impl;

import com.example.testproject1.configuration.xmlimportconfiguration.XmlImportConfiguration;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.importxmltodatabase.XmlToDataBaseImporter;
import com.example.testproject1.service.staffservice.StorageService;
import liquibase.pro.packaged.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Класс сохранения данных из XML в базу данных
 *
 * @author smigranov
 */
@Service
public class XmlToDataBaseImporterImpl implements XmlToDataBaseImporter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlToDataBaseImporterImpl.class);
    @Autowired
    private XmlImportConfiguration xmlImportConfiguration;

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveStaffInDb() {
        xmlImportConfiguration.getStorageServiceMap().forEach(this::save);
    }

    private <T>void save(StorageService<T> storageService, CrudService crudService) {
        for (T entity : storageService.getList()) {
            try {
                crudService.create(entity);
            } catch (DocflowRuntimeApplicationException e) {
                LOGGER.error("Ошибка сохранения Staff сущности в базу данных",e);
            }
        }
    }
}
