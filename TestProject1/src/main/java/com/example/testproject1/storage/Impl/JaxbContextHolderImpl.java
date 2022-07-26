package com.example.testproject1.storage.Impl;

import com.example.testproject1.model.DTO.DepartmentListXmlDTO;
import com.example.testproject1.model.DTO.OrganizationListXmlDTO;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import com.example.testproject1.model.DTO.PersonListXmlDTO;
import com.example.testproject1.storage.JaxbContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(JaxbContextHolderImpl.class);
    /**
     * Объект класса {@link JAXBContext}
     */
    private JAXBContext jaxbContext;

    @PostConstruct
    private void initContext(){
        try {
            jaxbContext = JAXBContext.newInstance(PersonListXmlDTO.class, DepartmentListXmlDTO.class
                    , OrganizationListXmlDTO.class, Person.class,Department.class,Organization.class);
            LOGGER.info("JaxbContext инициализирован классами: PersonListXmlDTO, DepartmentListXmlDTO" +
                    ", OrganizationListXmlDTO, Person, Department, Organization");
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JAXBContext getContext() {
        return jaxbContext;
    }
}