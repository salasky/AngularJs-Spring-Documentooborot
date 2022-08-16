package com.example.testproject1.service.importxmltodatabase.Impl;

import com.example.testproject1.configuration.xmlimportconfiguration.XmlImportConfiguration;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.importxmltodatabase.XmlToDataBaseImporter;
import com.example.testproject1.service.staffservice.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Класс сохранения данных из XML в базу данных
 *
 * @author smigranov
 */
@Service
public class XmlToDataBaseImporterImpl implements XmlToDataBaseImporter {
    @Autowired
    private XmlImportConfiguration xmlImportConfiguration;

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveStaffInDb() {
        xmlImportConfiguration.getStorageServiceMap().forEach(this::save);
    }

    private <T> void save(StorageService<T> storageService, CrudService crudService) {
        crudService.saveAll(storageService.getList());
    }
}
