package com.testing.hibernate.entity;

import com.testing.hibernate.entity.child.Vehicle;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by davidqi on 9/27/17.
 */
@Entity
@Table(name = "user_one_to_many_vehicle")
public class UserOneToOneVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date dob;
    @Transient
    private String hobbie;

    @OneToOne
    @JoinColumn(name = "vehicle_pk")
    private Vehicle vehicle;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }

    public String getHobbie() {
        return hobbie;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setHobbie(String hobbie) {
        this.hobbie = hobbie;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
