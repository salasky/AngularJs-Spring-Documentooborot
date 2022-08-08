package com.example.testproject1;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.model.staff.Organization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yaml")
class OrganizationRepositoriesTest {

    @Autowired
    @Qualifier("OrganizationRepository")
    private CrudRepositories organizationRepositories;

    @DisplayName("OrganizationRepositories create and getById test successful")
    @Test
    void organizationRepositoriesCreateTest() {
        Organization organization=new Organization();
        UUID uuid=UUID.randomUUID();
        organization.setId(uuid);
        organization.setFullName("Neft");
        organization.setShortName("NFT");
        organization.setSupervisor("VVP");
        organization.setContactNumber("678908i765");
        organizationRepositories.create(organization);
        Assertions.assertNotNull(organizationRepositories.getById(uuid.toString()));
        Organization organizationDB= (Organization) organizationRepositories.getById(uuid.toString()).get();
        Assertions.assertEquals("Neft",organizationDB.getFullName());
        Assertions.assertEquals("NFT",organizationDB.getShortName());
        Assertions.assertEquals("VVP",organizationDB.getSupervisor());
        Assertions.assertEquals("678908i765",organizationDB.getContactNumber());
    }

    @DisplayName("OrganizationRepositories deleteAll test successful")
    @Test
    void organizationRepositoriesDeleteAllTest() {
        Organization organization=new Organization();
        UUID uuid=UUID.randomUUID();
        organization.setId(uuid);
        organization.setFullName("OilAndGas");
        organization.setShortName("OGS");
        organization.setSupervisor("MMN");
        organization.setContactNumber("67328i765");
        organizationRepositories.create(organization);
        organizationRepositories.deleteAll();
        Assertions.assertEquals(0,organizationRepositories.getAll().size());
    }

    @DisplayName("OrganizationRepositories deleteById test successful")
    @Test
    void organizationRepositoriesDeleteByIdTest() {
        Organization organization=new Organization();
        UUID uuid=UUID.randomUUID();
        organization.setId(uuid);
        organization.setFullName("OilAndGas");
        organization.setShortName("OGS");
        organization.setSupervisor("MMN");
        organization.setContactNumber("67328i765");
        organizationRepositories.create(organization);
        organizationRepositories.deleteById(uuid.toString());
        Assertions.assertTrue(organizationRepositories.getById(uuid.toString()).isEmpty());
    }

    @DisplayName("OrganizationRepositories update test successful")
    @Test
    void organizationRepositoriesUpdateTest() {
        Organization organization=new Organization();
        UUID uuid=UUID.randomUUID();
        organization.setId(uuid);
        organization.setFullName("OilAndGas");
        organization.setShortName("OGS");
        organization.setSupervisor("MMN");
        organization.setContactNumber("67328i765");
        organizationRepositories.create(organization);
        organization.setFullName("NeftServices");
        organizationRepositories.update(organization);
        Assertions.assertNotNull(organizationRepositories.getById(uuid.toString()));
        Organization organizationDB= (Organization) organizationRepositories.getById(uuid.toString()).get();
        Assertions.assertEquals("NeftServices",organizationDB.getFullName());
    }
}
