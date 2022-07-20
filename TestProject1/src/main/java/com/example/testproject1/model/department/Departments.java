package com.example.testproject1.model.department;

import com.example.testproject1.model.department.Department;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Departments {
    @XmlElement(name = "person")
    private List<Department> list=new ArrayList<>();

    public Departments() {
    }

    public void setList(List<Department> list) {
        this.list = list;
    }

    public List<Department> getDepartmentList() {
        return list;
    }

    public boolean add(Department department){
        return list.add(department);
    }

    @Override
    public String toString() {
        return "Departments{" +
                "list=" + list +
                '}';
    }
}
