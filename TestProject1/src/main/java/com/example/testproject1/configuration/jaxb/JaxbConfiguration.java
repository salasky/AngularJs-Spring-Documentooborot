package com.example.testproject1.configuration.jaxb;

import com.example.testproject1.model.dto.staffdto.DepartmentListXmlDto;
import com.example.testproject1.model.dto.staffdto.JobTittleListXmlDto;
import com.example.testproject1.model.dto.staffdto.OrganizationListXmlDto;
import com.example.testproject1.model.dto.staffdto.PersonListXmlDto;
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
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonListXmlDto.class, DepartmentListXmlDto.class,
                    OrganizationListXmlDto.class, Person.class, Department.class, Organization.class, JobTittle.class, JobTittleListXmlDto.class);
            return jaxbContext;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
