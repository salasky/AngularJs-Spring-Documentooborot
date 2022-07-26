package com.example.testproject1.storage.Impl;

import com.example.testproject1.model.person.Department;
import com.example.testproject1.model.person.Organization;
import com.example.testproject1.model.person.Person;
import com.example.testproject1.model.person.PersonListXmlDTO;
import com.example.testproject1.storage.JaxbContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Класс реализующий интерфейс {@link JaxbContextHolder}
 *
 * @author smigranov
 */
@Component
public class JaxbContextHolderImpl implements JaxbContextHolder {

    /**
     * Объект класса {@link JAXBContext}
     */
    private JAXBContext jaxbContext;

    @PostConstruct
    private void initContext(){
        try {
            jaxbContext = JAXBContext.newInstance(PersonListXmlDTO.class, Department.class, Organization.class, Person.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public JAXBContext getContext() {
        return jaxbContext;
    }
}