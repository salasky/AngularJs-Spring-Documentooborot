package com.example.testproject1.jaxb;

import com.example.testproject1.model.person.Person;
import com.example.testproject1.model.person.Persons;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class PersonJaxbReader {
    public List<Person> getPerson(){
        try {
            JAXBContext jaxbContext=JAXBContext.newInstance(Persons.class);
            Unmarshaller u=jaxbContext.createUnmarshaller();
            FileReader reader=new FileReader("C:\\Users\\smigranov\\Desktop\\TestProject\\TestProject\\testrepo\\TestProject1\\src\\main\\resources\\persons.xml");
            Persons persons=(Persons) u.unmarshal(reader);
            List<Person> personlist=persons.getPersonList();
            return personlist;
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
