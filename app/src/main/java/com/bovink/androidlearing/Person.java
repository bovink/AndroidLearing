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

    public Person(String name, String age) {
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

}
