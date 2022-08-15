package com.example.testproject1.service.staffservice.impl;

import com.example.testproject1.model.dto.staff.JobTittleListDTO;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.service.jaxb.JaxbReader;
import com.example.testproject1.service.staffservice.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс реализующий интерфейс {@link StorageService}. Извлекает данные из xml.
 *
 * @author smigranov
 */
@Order(value = 2)
@Service
public class JobStorageService implements StorageService<JobTittle> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentStorageService.class);
    /**
     * Имя файла для jaxb анмаршалинга
     */
    private final String FILE_NAME = "jobs.xml";
    /**
     * Бин для чтения информации из xml файла
     */
    @Autowired
    private JaxbReader jaxbReader;

    /**
     * {@inheritDoc}
     */
    @Cacheable(cacheNames = "job")
    @Override
    public List<JobTittle> getList() {
        LOGGER.info("Begin find job ");
        JobTittleListDTO jobTittleListDTO = jaxbReader.jaxbXMLToObject(FILE_NAME);
        List<JobTittle> jobTittleList = jobTittleListDTO.getJobList();
        LOGGER.info("Find job result");
        return jobTittleList;
    }
}
