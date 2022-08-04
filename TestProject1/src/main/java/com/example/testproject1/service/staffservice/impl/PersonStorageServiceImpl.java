package com.example.testproject1.service.staffservice.impl;

import com.example.testproject1.model.dto.PersonListXmlDTO;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.jaxb.JaxbReader;
import com.example.testproject1.service.staffservice.PersonStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
     * Имя файла для jaxb анмаршалинга
     */
    private final String FILE_NAME = "personsFull.xml";
    /**
     * Бин для чтения информации из xml файла
     */
    @Autowired
    private JaxbReader jaxbReader;

    /**
     * {@inheritDoc}
     * Конфигурая кэширования в классе {@link com.example.testproject1.configuration.cache.CaffeineConfig}
     */
    @Cacheable(cacheNames = "person")
    @Override
    public List<Person> getPersonList() {
        LOGGER.info("Begin find Person in xml file");
        PersonListXmlDTO personListXmlDTO = jaxbReader.jaxbXMLToObject(FILE_NAME);
        List<Person> personList = personListXmlDTO.getPersonList();
        LOGGER.info("Find result");
        return personList;
    }
}