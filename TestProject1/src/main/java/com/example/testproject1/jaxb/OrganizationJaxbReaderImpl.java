package com.example.testproject1.jaxb;

import com.example.testproject1.model.organization.Organization;
import com.example.testproject1.model.organization.Organizations;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Класс реализующий интерфейс {@link OrganizationJaxbReader}
 *
 * @author smigranov
 */
@Service
public class OrganizationJaxbReaderImpl implements OrganizationJaxbReader{
    /**
     * Хранит путь к файлу для анмаршалинга объектов класса {@link Organization}
     */
    private static final String PATH="C:\\Users\\smigranov\\Desktop\\TestProject\\TestProject\\testrepo\\TestProject1\\src\\main\\resources\\organizations.xml";

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<Organization> getOrganization(){
        try {
            JAXBContext jaxbContext=JAXBContext.newInstance(Organizations.class);
            Unmarshaller u=jaxbContext.createUnmarshaller();
            FileReader reader=new FileReader(PATH);
            Organizations organizations=(Organizations) u.unmarshal(reader);
            List<Organization> organizationList=organizations.getOrganizationList();
            return organizationList;
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
