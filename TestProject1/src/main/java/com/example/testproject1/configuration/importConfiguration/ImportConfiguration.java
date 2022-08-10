package com.example.testproject1.configuration.importConfiguration;

import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.staffservice.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс для конфигурации импорта xml в базу данных
 *
 * @author smigranov
 */
@Configuration
public class ImportConfiguration {
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

    Map<StorageService,CrudService> serviceMap=new LinkedHashMap<>();

    @Bean
    public Map<StorageService, CrudService> getStorageServiceMap() {
        serviceMap.put(organizationStorageService, organizationCrudService);
        serviceMap.put(jobStorageService, jobTittleCrudService);
        serviceMap.put(departmentStorageService, departmentCrudService);
        serviceMap.put(personStorageService, personCrudService);
        return serviceMap;
    }
}
