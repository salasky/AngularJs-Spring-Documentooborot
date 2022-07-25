package com.example.testproject1.service.jaxb;

import com.example.testproject1.model.person.Person;
import com.example.testproject1.storage.JaxbContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;

/**
 * Класс реализующий интерфейс {@link JaxbReader}
 *
 * @author smigranov
 */
@Service
public class JaxbReaderImpl implements JaxbReader {
    /**
     * Путь к xml файлу с объектами {@link Person}
     */
    private final String PATH= this.getClass().getClassLoader().getResource("persons.xml").getPath();

    @Autowired
    private JaxbContextHolder jaxbContextHolder;
    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public  <T> T jaxbXMLToObject(String xmlData, Class<T> clazz) {
        try (FileReader reader=new FileReader(xmlData)) {
            T result = null;
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            result= (T) unmarshaller.unmarshal(reader);
            return result; // Ofcourse this is after conversion
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
