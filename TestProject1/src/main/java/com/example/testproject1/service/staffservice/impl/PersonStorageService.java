package com.example.testproject1.service.staffservice.impl;

import com.example.testproject1.model.dto.staffdto.PersonListXmlDto;
import com.example.testproject1.model.staff.Person;
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
 * Класс сохранения и получения объектов {@link Person}
 *
 * @author smigranov
 */
@Order(value = 4)
@Service
public class PersonStorageService implements StorageService<Person> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonStorageService.class);
    /**
     * Имя файла для jaxb анмаршалинга
     */
    private final String FILE_NAME_PERSONS = "persons.xml";


    @Autowired
    private JaxbReader jaxbReader;

    /**
     * {@inheritDoc}
     * Конфигурая кэширования в классе {@link com.example.testproject1.configuration.cache.CaffeineConfig}
     */
    @Cacheable(cacheNames = "person")
    @Override
    public List<Person> getList() {
        LOGGER.info("Begin find Person in xml file");
        PersonListXmlDto personListXmlDto = jaxbReader.jaxbXMLToObject(FILE_NAME_PERSONS);
        List<Person> personList = personListXmlDto.getPersonList();
        LOGGER.info("Find result");
        return personList;
    }
}
