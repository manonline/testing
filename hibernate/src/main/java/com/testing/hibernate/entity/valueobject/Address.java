package com.testing.hibernate.entity.valueobject;

import javax.persistence.Embeddable;

/**
 * Created by davidqi on 9/26/17.
 */
@Embeddable
public class Address {
    private String state;
    private String city;
    private String street;

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
