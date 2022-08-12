package com.example.testproject1;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.staff.Organization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

    @BeforeAll
    public static void init(
            @Autowired CrudRepository<Organization> organizationRepository) throws DocflowRuntimeApplicationException {
        if (organizationRepository.getAll().size() == 0) {
            Organization organization = Organization.newBuilder()
                    .setId(UUID.randomUUID())
                    .setFullName("FullName")
                    .setShortName("organizationShortName")
                    .setSupervisor("orgSupervisor")
                    .setContactNumber("897643567895").build();
            organizationRepository.create(organization);
        }
    }

    private Organization getOrganization() {
        return Organization.newBuilder()
                .setId(UUID.randomUUID())
                .setFullName("FullName")
                .setShortName("organizationShortName")
                .setSupervisor("orgSupervisor")
                .setContactNumber("897643567895").build();
    }

    @DisplayName("OrganizationRepository create test successful")
    @Test
    void organizationRepositoryCreateTest() throws DocflowRuntimeApplicationException {
        Organization organizationEx = getOrganization();
        UUID uuid = organizationEx.getId();
        organizationCrudRepository.create(organizationEx);
        Assertions.assertTrue(organizationCrudRepository.getById(uuid).isPresent());
        Organization organizationActual = organizationCrudRepository.getById(uuid).get();
        Assertions.assertEquals(organizationEx, organizationActual);
    }

    @DisplayName("OrganizationRepository deleteAll test successful")
    @Test
    void organizationRepositoryDeleteAllTest() throws DocflowRuntimeApplicationException {
        if (organizationCrudRepository.getAll().isEmpty()) {
            organizationCrudRepository.create(getOrganization());
        }
        organizationCrudRepository.deleteAll();
        Assertions.assertTrue(organizationCrudRepository.getAll().isEmpty());
    }

    @DisplayName("OrganizationRepository deleteById test successful")
    @Test
    void organizationRepositoryDeleteByIdTest() throws DocflowRuntimeApplicationException {
        Organization organization = getOrganization();
        UUID uuid = organization.getId();
        organizationCrudRepository.create(organization);
        organizationCrudRepository.deleteById(uuid);
        Assertions.assertTrue(organizationCrudRepository.getById(uuid).isEmpty());
    }

    @DisplayName("OrganizationRepository update test successful")
    @Test
    void organizationRepositoryUpdateTest() throws DocflowRuntimeApplicationException {
        Organization organization = getOrganization();
        UUID uuid = organization.getId();
        organizationCrudRepository.create(organization);
        organization.setFullName("NeftServicesTest");
        organizationCrudRepository.update(organization);

        Assertions.assertTrue(organizationCrudRepository.getById(uuid).isPresent());
        Organization organizationDB = organizationCrudRepository.getById(uuid).get();

        organizationCrudRepository.update(organization);

        Assertions.assertEquals("NeftServicesTest", organizationDB.getFullName());
    }
}
