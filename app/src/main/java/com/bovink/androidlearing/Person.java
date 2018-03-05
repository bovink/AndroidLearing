package com.bovink.androidlearing;

/**
 * @author fox
 * @since 2017/11/15
 */

public class Person {

    private String name;
    private String age;
    private boolean hasName;

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

    public boolean isHasName() {
        return name != null;
    }

}
