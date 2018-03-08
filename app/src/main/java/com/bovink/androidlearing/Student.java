package com.bovink.androidlearing;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * @author fox
 * @since 2018/03/08
 */

public class Student extends BaseObservable {

    private String name;
    private String age;

    public Student(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
}
