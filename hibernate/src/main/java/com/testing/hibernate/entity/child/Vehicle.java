package com.testing.hibernate.entity.child;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by davidqi on 9/27/17.
 */
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String vehicleName;

    public int getId() {
        return id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
