package com.testing.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by davidqi on 9/27/17.
 */
@Entity
@Table(name = "user_many_to_many_vehicle")
public class UserManyToManyVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dob;
    @Transient
    private String hobbie;

    @ManyToMany
    private Collection<VehicleManyToManyUser> vehicleManyToManyUser = new ArrayList<VehicleManyToManyUser>();

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

    public Collection<VehicleManyToManyUser> getVehicleManyToManyUser() {
        return vehicleManyToManyUser;
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

    public void setVehicleManyToManyUser(Collection<VehicleManyToManyUser> vehicleManyToManyUser) {
        this.vehicleManyToManyUser = vehicleManyToManyUser;
    }
}
