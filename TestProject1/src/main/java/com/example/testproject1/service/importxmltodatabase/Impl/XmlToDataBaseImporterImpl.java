package com.example.testproject1.service.importxmltodatabase.Impl;

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

    /**
     * Бин CrudService Person
     */
    @Autowired
    private CrudService<Person> personCrudService;
    /**
     * Бин CrudService Department
     */
    @Autowired
    private CrudService<Department> departmentCrudService;
    /**
     * Бин CrudService Organization
     */
    @Autowired
    private CrudService<Organization> organizationCrudService;
    /**
     * Бин CrudService JobTittle
     */
    @Autowired
    private CrudService<JobTittle> jobTittleCrudService;
    /**
     * Бин сервиса для работы с person xml
     */
    @Autowired
    private StorageService<Person> personStorageService;
    /**
     * Бин сервиса для работы с Department xml
     */
    @Autowired
    private StorageService<Department> departmentStorageService;
    /**
     * Бин сервиса для работы с Organization xml
     */
    @Autowired
    private StorageService<Organization> organizationStorageService;
    /**
     * Бин сервиса для работы с Job xml
     */
    @Autowired
    private StorageService<JobTittle> jobStorageService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveStaffInDb(){
        save(organizationStorageService,organizationCrudService);
        save(jobStorageService,jobTittleCrudService);
        save(departmentStorageService,departmentCrudService);
        save(personStorageService,personCrudService);
    }


    private void save(StorageService storageService,CrudService crudService){
        for (Object obj: storageService.getList()
             ) {
            crudService.create(obj);
        }
    }
}
