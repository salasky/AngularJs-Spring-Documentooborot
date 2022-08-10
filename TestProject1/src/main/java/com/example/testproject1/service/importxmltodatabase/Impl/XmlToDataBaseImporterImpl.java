package com.example.testproject1.service.importxmltodatabase.Impl;

import com.example.testproject1.configuration.importConfiguration.ImportCongiguration;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
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
    private ImportCongiguration importCongiguration;
    /**
     * {@inheritDoc}
     */
    @Override
    public void saveStaffInDb(){
        importCongiguration.getStorageServiceMap().forEach(this::save);
    }

    private void save(StorageService storageService,CrudService crudService){

        storageService.getList().forEach(crudService::create);
    }
}
