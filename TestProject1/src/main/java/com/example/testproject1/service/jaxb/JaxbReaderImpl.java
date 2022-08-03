package com.example.testproject1.service.jaxb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.text.MessageFormat;

/**
 * Класс реализующий интерфейс {@link JaxbReader}
 * Для unmarshalling-а xml файлов любого типа
 *
 * @author smigranov
 */
@Service
public class JaxbReaderImpl implements JaxbReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(JaxbReaderImpl.class);
    /**
     * Бин jaxb context-а
     */
    @Autowired
    private JAXBContext jaxbContext;

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T jaxbXMLToObject(String fileName) {
        if (fileName != null) {
            String path = this.getClass().getClassLoader().getResource(fileName).getPath();
            try (FileReader reader = new FileReader(path)) {
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                T result = result = (T) unmarshaller.unmarshal(reader);
                return result;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        LOGGER.error(MessageFormat.format("Файл {0} не найден", fileName));
        return null;
    }
}
