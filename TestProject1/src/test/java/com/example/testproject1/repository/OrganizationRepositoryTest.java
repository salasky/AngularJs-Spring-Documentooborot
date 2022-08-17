package com.example.testproject1.repository;

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

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;
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
                    .setContactNumber(List.of("897643567895")).build();
            organizationRepository.create(organization);
        }
    }

    private Organization getOrganization() {
        return Organization.newBuilder()
                .setId(UUID.randomUUID())
                .setFullName("FullName")
                .setShortName("organizationShortName")
                .setSupervisor("orgSupervisor")
                .setContactNumber(List.of("897643567895")).build();
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

    @DisplayName("OrganizationRepository deleteAll test")
    @Test
    void organizationRepositoryDeleteAllTest() throws DocflowRuntimeApplicationException {
        if (organizationCrudRepository.getAll().isEmpty()) {
            organizationCrudRepository.create(getOrganization());
        }
        organizationCrudRepository.deleteAll();
        Assertions.assertTrue(organizationCrudRepository.getAll().isEmpty());
    }

    @DisplayName("OrganizationRepository deleteById test")
    @Test
    void organizationRepositoryDeleteByIdTest() throws DocflowRuntimeApplicationException {
        Organization organization = getOrganization();
        UUID uuid = organization.getId();
        organizationCrudRepository.create(organization);
        organizationCrudRepository.deleteById(uuid);
        Assertions.assertTrue(organizationCrudRepository.getById(uuid).isEmpty());
    }

    @DisplayName("OrganizationRepository update test")
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

    @DisplayName("OrganizationRepository saveAll test")
    @Test
    void organizationRepositorySaveALLTest() throws DocflowRuntimeApplicationException {
        Organization organization = getOrganization();
        Organization organizationSecond = getOrganization();
        UUID uuid = organization.getId();
        UUID uuidSecond = organizationSecond.getId();
        List<Organization> organizationList = new ArrayList<>();
        organizationList.add(organization);
        organizationList.add(organizationSecond);
        try {
            organizationCrudRepository.saveAll(organizationList);
        } catch (BatchUpdateException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(organizationCrudRepository.getById(uuid).isPresent());
        Assertions.assertTrue(organizationCrudRepository.getById(uuidSecond).isPresent());
    }
}
