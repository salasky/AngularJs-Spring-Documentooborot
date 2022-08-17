package com.example.testproject1.model.dto.staffdto;

import com.example.testproject1.model.staff.Organization;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class OrganizationListForMapping {
    /**
     * List Организаций
     */
    private List<OrganizationDTO> organizationList = new ArrayList<>();

    /**
     * Метод получения списка организаций
     *
     * @return {@link List} объектов {@link Organization}
     */
    @JsonProperty("list")
    public final List<OrganizationDTO> getOrganizationList() {
        return organizationList;
    }
}
