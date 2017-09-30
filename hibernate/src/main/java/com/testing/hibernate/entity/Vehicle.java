package com.testing.hibernate.entity;

import javax.persistence.*;

/**
 * Created by davidqi on 9/27/17.
 */
@Entity
// @Inheritance is optional, by default single table strategy is used
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)
/**
 * @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) :
 *              separate table for each class with all fields inherited
 * @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) :
 */
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
