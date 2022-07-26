package com.example.testproject1.storage.Impl;

import com.example.testproject1.model.DTO.PersonListXmlDTO;
import com.example.testproject1.service.jaxb.JaxbReader;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.storage.PersonHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс сохранения и получения объектов {@link Person}
 *
 * @author smigranov
 */
@Service
public class PersonHolderImpl implements PersonHolder {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonHolderImpl.class);
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
     */
    @Cacheable(cacheNames = "person")
    @Override
    public List<Person> getPersonListList() {
        LOGGER.info("Begin find Person ");
        PersonListXmlDTO personListXmlDTO = jaxbReader.jaxbXMLToObject(PersonListXmlDTO.class);
        personList= personListXmlDTO.getPersonList();
        LOGGER.info("Find Person result");
        return personList;
    }
}
