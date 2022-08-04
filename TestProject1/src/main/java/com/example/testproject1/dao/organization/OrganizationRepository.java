package com.example.testproject1.dao.organization;

import com.example.testproject1.model.staff.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository {
    Integer create(Organization organization);

    Integer update(Organization organization);

    List<Organization> getAll();

    Optional<Organization> getById(String uuid);

    Integer deleteAll();

    Integer deleteById(String id);

    boolean existById(String uuid);
}
