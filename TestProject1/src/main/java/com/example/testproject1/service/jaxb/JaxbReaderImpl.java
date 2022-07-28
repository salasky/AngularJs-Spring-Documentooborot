package com.example.testproject1.service.jaxb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
     * Имя xml файла
     */
    @Value("${jaxb.fileName}")
    private String fileName;
    /**
     * Бин jaxb context-а
     */
    @Autowired
    private JAXBContext jaxbContext;

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T jaxbXMLToObject() {
        String path = this.getClass().getClassLoader().getResource(fileName).getPath();
        try (FileReader reader = new FileReader(path)) {
            T result = null;
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            result = (T) unmarshaller.unmarshal(reader);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
