package com.bovink.androidlearing.model;

/**
 * com.bovink.androidlearing.model
 *
 * @author bovink
 * @since 2017/6/24
 */

public class City {

    private String country_code;
    private String district;
    private String name;
    private String id;
    private String population;

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
}
