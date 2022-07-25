package com.example.testproject1.storage.Impl;

import com.example.testproject1.model.person.Persons;
import com.example.testproject1.service.jaxb.JaxbReader;
import com.example.testproject1.model.person.Person;
import com.example.testproject1.storage.PersonHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonHolderImpl implements PersonHolder {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonHolderImpl.class);
    /**
     * Бин для чтения информации из xml файла
     */
    @Autowired
    private JaxbReader jaxbReader;
    /**
     * Лист для сохранения объектов унаследованных от {@link com.example.testproject1.model.person.Person}
     */
    public List<Person> personList = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Cacheable(cacheNames = "person")
    @Override
    public List<Person> getPersonListList() {
        LOGGER.info("Begin find Person ");
        Persons persons = jaxbReader.jaxbXMLToObject(this.getClass().getClassLoader()
                .getResource("persons.xml").getPath(), Persons.class);
        personList=persons.getPersonList();
        LOGGER.info("Find Person result");
        return personList;
    }
}
