package com.example.testproject1.configuration.jaxb;

import com.example.testproject1.model.DTO.DepartmentListXmlDTO;
import com.example.testproject1.model.DTO.OrganizationListXmlDTO;
import com.example.testproject1.model.DTO.PersonListXmlDTO;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Configuration
public class JaxbConfiguration {
    @Bean(name = "BeanJaxbContext")
    public JAXBContext getContext()
    {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonListXmlDTO.class, DepartmentListXmlDTO.class
                    , OrganizationListXmlDTO.class, Person.class, Department.class, Organization.class);
            return jaxbContext;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
