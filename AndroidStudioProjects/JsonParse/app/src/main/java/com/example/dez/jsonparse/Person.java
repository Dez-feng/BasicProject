package com.example.dez.jsonparse;

import java.util.List;
/**
 * Created by Dez on 2017/7/4.
 */

public class Person {

    private String name;
    private int age;
    private List<String> phones;

    public List<String> getPhones(){

        return phones;
    }

    public void setPhones(List<String> phones){

        this.phones = phones;
    }

    public Person() {
        super();
    }

    public Person(String name, int age, List<String> phones) {
        super();
        this.name = name;
        this.age = age;
        this.phones = phones;

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}






