package com.example.testproject1.service.importxmltodatabase.Impl;

import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.importxmltodatabase.ImportXml;
import com.example.testproject1.service.staffservice.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс сохранения данных из XML в базу данных
 *
 * @author smigranov
 */
@Service
public class ImportXmlmpl implements ImportXml {

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
        saveOrganization();
        saveDepartment();
        saveJob();
        savePerson();
    }

    /**
     * Метод сохранения организации
     */
    private void saveOrganization(){

        for (Organization organization :organizationStorageService.getList()
        ) {
            organizationCrudService.create(organization);
        }
    }
    /**
     * Метод сохранения department
     */
    private void saveDepartment(){
        for (Department department : departmentStorageService.getList()
        ) {
            departmentCrudService.create(department);
        }
    }
    /**
     * Метод сохранения Job
     */
    private void saveJob(){
        for (JobTittle jobTittle :jobStorageService.getList()
        ) {
            jobTittleCrudService.create(jobTittle);
        }
    }    /**
     * Метод сохранения Person
     */

    private void savePerson(){
        for (Person person:personStorageService.getList()
        ) {
            personCrudService.create(person);
        }
    }
}
