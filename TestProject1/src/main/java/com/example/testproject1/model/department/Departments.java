package com.example.testproject1.model.department;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с JAXB
 *
 * @author smigranov
 */
@XmlRootElement
public class Departments {
    /**
     * List Подразделений
     */
    @XmlElement(name = "person")
    private List<Department> list=new ArrayList<>();

    public Departments() {
    }

    public void setList(List<Department> list) {
        this.list = list;
    }

    /**
     * Метод получения списка подразделений
     * @return
     */
    public List<Department> getDepartmentList() {
        return list;
    }

    /**
     * Метод добавления подразделений в list
     * @param department
     * @return
     */
    public boolean add(Department department){
        return list.add(department);
    }
}
