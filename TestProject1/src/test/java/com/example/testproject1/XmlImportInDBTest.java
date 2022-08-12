package com.example.testproject1;

import com.example.testproject1.model.staff.Person;
import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.importxmltodatabase.XmlToDataBaseImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;



@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yaml")
public class XmlImportInDBTest {
    @Autowired
    private XmlToDataBaseImporter xmlToDataBaseImporter;
    @Autowired
    private CrudService<Person> personCrudService;

    @DisplayName("XmlImportInDBTest import test")
    @Test
    void xmlImportInDBTestTest() {
        xmlToDataBaseImporter.saveStaffInDb();
        Assertions.assertFalse(personCrudService.getAll().isEmpty());
    }
}
