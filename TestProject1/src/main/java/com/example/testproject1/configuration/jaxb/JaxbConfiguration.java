package com.example.testproject1.configuration.jaxb;

import com.example.testproject1.model.dto.DepartmentListDTO;
import com.example.testproject1.model.dto.JobTittleListDTO;
import com.example.testproject1.model.dto.OrganizationListDTO;
import com.example.testproject1.model.dto.PersonListDTO;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.model.staff.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Класс для конфишурации JAXBContext.
 * Создания инициализированного бина JAXBContext.
 *
 * @author smigranov
 */
@Configuration
public class JaxbConfiguration {
    /**
     * Метод получения бина JAXBContext
     *
     * @return озвращает сконфигурированный JAXBContext
     */
    @Bean
    public JAXBContext getContext() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonListDTO.class, DepartmentListDTO.class,
                    OrganizationListDTO.class, Person.class, Department.class, Organization.class, JobTittle.class, JobTittleListDTO.class);
            return jaxbContext;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
