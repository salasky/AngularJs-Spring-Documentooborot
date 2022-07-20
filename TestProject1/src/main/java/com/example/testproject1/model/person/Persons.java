package com.example.testproject1.model.person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Persons {
    @XmlElement(name = "person")
    private List<Person> list=new ArrayList<>();

    public Persons() {
    }

    public void setList(List<Person> list) {
        this.list = list;
    }

    public List<Person> getPersonList() {
        return list;
    }

    public boolean add(Person person){
        return list.add(person);
    }

    @Override
    public String toString() {
        return "Persons{" +
                "list=" + list +
                '}';
    }
}
