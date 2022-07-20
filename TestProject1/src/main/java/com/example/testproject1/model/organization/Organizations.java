package com.example.testproject1.model.organization;

import com.example.testproject1.model.organization.Organization;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Organizations {
    @XmlElement(name = "organization")
    private List<Organization> list=new ArrayList<>();

    public List<Organization> getOrganizationList() {
        return list;
    }

    public void setList(List<Organization> list) {
        this.list = list;
    }
    public boolean add(Organization organization){
        return list.add(organization);
    }

    @Override
    public String toString() {
        return "Organizations{" +
                "list=" + list +
                '}';
    }
}
