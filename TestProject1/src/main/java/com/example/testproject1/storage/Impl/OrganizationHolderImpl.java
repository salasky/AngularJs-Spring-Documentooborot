package com.example.testproject1.storage.Impl;

import com.example.testproject1.model.DTO.DepartmentListXmlDTO;
import com.example.testproject1.model.DTO.OrganizationListXmlDTO;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.jaxb.JaxbReader;
import com.example.testproject1.storage.OrganizationHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для хранения и получения {@link Organization}
 *
 * @author smigranov
 */
@Service
public class OrganizationHolderImpl implements OrganizationHolder {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationHolderImpl.class);
    /**
     * Бин для чтения информации из xml файла
     */
    @Autowired
    private JaxbReader jaxbReader;
    /**
     * Лист для сохранения объектов {@link Organization}
     */
    public List<Organization> organizationListList = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Cacheable(cacheNames = "organization")
    @Override
    public List<Organization> getOrganizationList() {
        LOGGER.info("Begin find Organization ");
        OrganizationListXmlDTO organizationListXmlDTO = jaxbReader.jaxbXMLToObject();
        organizationListList= organizationListXmlDTO.getOrganizationList();
        LOGGER.info("Find Organization result");
        return organizationListList;
    }
}
