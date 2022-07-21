package com.example.testproject1.storage.Impl;

import com.example.testproject1.jaxb.PersonJaxbReader;
import com.example.testproject1.model.BaseDocument;
import com.example.testproject1.model.person.Person;
import com.example.testproject1.service.documents.impl.GenerateReportServiceImpl;
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
    @Autowired
    private PersonJaxbReader personJaxbReader;
    /**
     * Лист для сохранения объектов унаследованных от {@link com.example.testproject1.model.person.Person}
     */
    public static List<Person> personList = new ArrayList<>();

    @Cacheable(cacheNames = "person")
    @Override
    public List<Person> getPersonListList() {
        LOGGER.info("Begin find Person ");
        personList = personJaxbReader.getPerson();
        LOGGER.info("Find Person result");
        return personList;
    }
}
