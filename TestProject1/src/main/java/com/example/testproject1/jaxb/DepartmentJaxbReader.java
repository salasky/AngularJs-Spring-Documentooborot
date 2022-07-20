package com.example.testproject1.jaxb;

import com.example.testproject1.model.department.Department;
import com.example.testproject1.model.department.Departments;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class DepartmentJaxbReader {
    public List<Department> getDepartment(){
        try {
            JAXBContext jaxbContext=JAXBContext.newInstance(Departments.class);
            Unmarshaller u=jaxbContext.createUnmarshaller();
            FileReader reader=new FileReader("C:\\Users\\smigranov\\Desktop\\TestProject\\TestProject\\testrepo\\TestProject1\\src\\main\\resources\\departments.xml");
            Departments departments=(Departments) u.unmarshal(reader);
            List<Department> departmentList=departments.getDepartmentList();
            return departmentList;
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
