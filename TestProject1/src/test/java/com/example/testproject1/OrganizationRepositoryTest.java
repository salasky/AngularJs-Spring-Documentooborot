package com.example.testproject1;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.model.staff.Organization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yaml")
class OrganizationRepositoryTest {

    @Autowired
    private CrudRepository<Organization> organizationCrudRepository;

    @DisplayName("OrganizationRepository create and getById test successful")
    @Test
    void organizationRepositoryCreateTest() {
        Organization organization = new Organization();
        UUID uuid = UUID.randomUUID();
        organization.setId(uuid);
        organization.setFullName("Neft");
        organization.setShortName("NFT");
        organization.setSupervisor("VVP");
        organization.setContactNumber("678908i765");
        organizationCrudRepository.create(organization);
        Assertions.assertTrue(organizationCrudRepository.getById(uuid.toString()).isPresent());
        Organization organizationDB = organizationCrudRepository.getById(uuid.toString()).get();
        Assertions.assertEquals("Neft", organizationDB.getFullName());
        Assertions.assertEquals("NFT", organizationDB.getShortName());
        Assertions.assertEquals("VVP", organizationDB.getSupervisor());
        Assertions.assertEquals("678908i765", organizationDB.getContactNumber());
    }

    @DisplayName("OrganizationRepository deleteAll test successful")
    @Test
    void organizationRepositoryDeleteAllTest() {
        Organization organization = new Organization();
        UUID uuid = UUID.randomUUID();
        organization.setId(uuid);
        organization.setFullName("OilAndGas");
        organization.setShortName("OGS");
        organization.setSupervisor("MMN");
        organization.setContactNumber("67328i765");
        organizationCrudRepository.create(organization);
        organizationCrudRepository.deleteAll();
        Assertions.assertTrue(organizationCrudRepository.getAll().isEmpty());
        Assertions.assertEquals(0, organizationCrudRepository.getAll().size());
    }

    @DisplayName("OrganizationRepository deleteById test successful")
    @Test
    void organizationRepositoryDeleteByIdTest() {
        Organization organization = new Organization();
        UUID uuid = UUID.randomUUID();
        organization.setId(uuid);
        organization.setFullName("OilAndGas");
        organization.setShortName("OGS");
        organization.setSupervisor("MMN");
        organization.setContactNumber("67328i765");
        organizationCrudRepository.create(organization);
        organizationCrudRepository.deleteById(uuid.toString());
        Assertions.assertTrue(organizationCrudRepository.getById(uuid.toString()).isEmpty());
    }

    @DisplayName("OrganizationRepository update test successful")
    @Test
    void organizationRepositoryUpdateTest() {
        Organization organization = new Organization();
        UUID uuid = UUID.randomUUID();
        organization.setId(uuid);
        organization.setFullName("OilAndGas");
        organization.setShortName("OGS");
        organization.setSupervisor("MMN");
        organization.setContactNumber("67328i765");
        organizationCrudRepository.create(organization);
        organization.setFullName("NeftServices");
        organizationCrudRepository.update(organization);
        Assertions.assertTrue(organizationCrudRepository.getById(uuid.toString()).isPresent());
        Organization organizationDB = organizationCrudRepository.getById(uuid.toString()).get();
        Assertions.assertEquals("NeftServices", organizationDB.getFullName());
    }
}
