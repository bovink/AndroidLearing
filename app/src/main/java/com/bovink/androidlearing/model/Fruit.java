package com.bovink.androidlearing.model;

/**
 * com.bovink.androidlearing.model
 *
 * @author bovink
 * @since 2017/9/18
 */

public class Fruit {

    private String name;
    private String price;

    public Fruit(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
