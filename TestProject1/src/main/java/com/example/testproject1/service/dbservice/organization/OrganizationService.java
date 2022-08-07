package com.example.testproject1.service.dbservice.organization;

import com.example.testproject1.model.staff.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    Optional<Organization> create(Organization organization);

    List<Organization> getall();

    Optional<Organization> getById(String id);

    Optional<Organization> update(Organization organization);

    void deleteAll();

    void deleteById(String id);
}
