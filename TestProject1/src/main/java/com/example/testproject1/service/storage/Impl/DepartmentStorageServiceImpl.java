package com.example.testproject1.service.storage.Impl;

import com.example.testproject1.configuration.cache.CaffeineConfig;
import com.example.testproject1.model.DTO.DepartmentListXmlDTO;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.service.jaxb.JaxbReader;
import com.example.testproject1.service.storage.DepartmentStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс сохранения и получения объектов {@link Department}
 *
 * @author smigranov
 */
@Service
public class DepartmentStorageServiceImpl implements DepartmentStorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentStorageServiceImpl.class);
    /**
     * Бин для чтения информации из xml файла
     */
    @Autowired
    private JaxbReader jaxbReader;
    /**
     * Лист для сохранения объектов {@link Department}
     */
    public List<Department> departmentListList = new ArrayList<>();

    /**
     * {@inheritDoc}
     * Конфигурая кэширования в классе {@link CaffeineConfig}
     */
    @Cacheable(cacheNames = "department")
    @Override
    public List<Department> getDepartmentList() {
        LOGGER.info("Begin find Department ");
        DepartmentListXmlDTO departmentListXmlDTO = jaxbReader.jaxbXMLToObject();
        departmentListList= departmentListXmlDTO.getDepartmentList();
        LOGGER.info("Find Department result");
        return departmentListList;
    }
}