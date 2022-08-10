package com.example.testproject1.service.importxmltodatabase.Impl;

import com.example.testproject1.configuration.importConfiguration.ImportConfiguration;
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
    private ImportConfiguration importConfiguration;
    /**
     * {@inheritDoc}
     */
    @Override
    public void saveStaffInDb(){
        importConfiguration.getStorageServiceMap().forEach(this::save);
    }

    private void save(StorageService storageService,CrudService crudService){

        storageService.getList().forEach(crudService::create);
    }
}
