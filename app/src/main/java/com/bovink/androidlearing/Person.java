package com.bovink.androidlearing;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * @author fox
 * @since 2017/11/15
 */

public class Person extends BaseObservable {

    private String name;
    private String age;
    private boolean hasName;
    private int number1;
    private int number2;

    public Person(String name, String age, int number1, int number2) {
        this.name = name;
        this.age = age;
        this.number1 = number1;
        this.number2 = number2;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
        notifyPropertyChanged(BR.hasName);
    }

    @Bindable
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public boolean isHasName() {
        return name != null;
    }

    @Bindable
    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
        notifyPropertyChanged(BR.number1);
    }

    @Bindable
    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
        notifyPropertyChanged(BR.number2);
    }
}
