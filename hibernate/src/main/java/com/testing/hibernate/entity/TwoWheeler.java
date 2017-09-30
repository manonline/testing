package com.testing.hibernate.entity;

import javax.persistence.*;

@Entity
// value to flag this type of objects in parent object table
@DiscriminatorValue("Bike")
public class TwoWheeler extends Vehicle {
    private String SteeringHandle;

    public String getSteeringHandle() {

        return SteeringHandle;
    }

    public void setSteeringHandle(String steeringHandle) {
        SteeringHandle = steeringHandle;
    }
}
