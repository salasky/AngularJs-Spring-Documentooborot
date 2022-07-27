package com.example.testproject1.service.storage.Impl;

import com.example.testproject1.model.DTO.PersonListXmlDTO;
import com.example.testproject1.service.jaxb.JaxbReader;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.storage.PersonStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.testproject1.configuration.cache.CaffeineConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс сохранения и получения объектов {@link Person}
 *
 * @author smigranov
 */
@Service
public class PersonStorageServiceImpl implements PersonStorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonStorageServiceImpl.class);
    /**
     * Бин для чтения информации из xml файла
     */
    @Autowired
    private JaxbReader jaxbReader;
    /**
     * Лист для сохранения объектов унаследованных от {@link com.example.testproject1.model.staff.Person}
     */
    public List<Person> personList = new ArrayList<>();

    /**
     * {@inheritDoc}
     * Конфигурая кэширования в классе {@link CaffeineConfig}
     */
    @Cacheable(cacheNames = "person")
    @Override
    public List<Person> getPersonList() {
        LOGGER.info("Begin find Person in xml file");
        PersonListXmlDTO personListXmlDTO = jaxbReader.jaxbXMLToObject();
        personList= personListXmlDTO.getPersonList();
        LOGGER.info("Find result");
        return personList;
    }
}