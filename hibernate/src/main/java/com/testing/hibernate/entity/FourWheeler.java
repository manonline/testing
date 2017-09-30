package com.testing.hibernate.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
// value to flag this type of objects in parent object table
@DiscriminatorValue("Prosche")
public class FourWheeler extends Vehicle {
    private String SteeringWheel;

    public void setSteeringWheel(String steeringWheel) {
        SteeringWheel = steeringWheel;
    }

    public String getSteeringWheel() {

        return SteeringWheel;
    }
}
