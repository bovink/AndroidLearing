package com.bovink.androidlearing.model;

/**
 * @author fox
 * @since 2017/09/21
 */

public class Person {

    private String name;
    private String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void print() {
        System.out.println("name = " + name);
        System.out.println("age = " + age);
    }
}
