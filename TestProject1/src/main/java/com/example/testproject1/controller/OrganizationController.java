package com.example.testproject1.controller;

import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.staff.Organization;
import com.example.testproject1.service.dbservice.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private CrudService<Organization> organizationCrudService;

    @GetMapping
    public ResponseEntity<List<Organization>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(organizationCrudService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organization> getById(@PathVariable String id) {
        Optional<Organization> organization=organizationCrudService.getById(id);
        if(organization.isPresent()){
            throw new RuntimeException();
            /*return ResponseEntity.status(HttpStatus.OK).body(organizationCrudService.getById(id).get());*/
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/add")
    public ResponseEntity<Organization> addOrganization(@RequestBody Organization organization) throws DocflowRuntimeApplicationException {
        /*return ResponseEntity.status(HttpStatus.CREATED).body(organizationCrudService.create(organization));*/
        throw new DocflowRuntimeApplicationException("d");
    }



}
