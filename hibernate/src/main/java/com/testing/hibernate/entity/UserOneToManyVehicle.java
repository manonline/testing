package com.testing.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by davidqi on 9/27/17.
 */
@Entity
@Table(name = "user_one_to_many_vehicle")
public class UserOneToManyVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dob;
    @Transient
    private String hobbie;

    // test only one direction, i.e. no @ManyToOne from the other side
    // @JoinTable to manage the attributes of the linkage table
    // Cascade to enable auto-save associated objects is mapping is set,
    // i.e. user.saveVehicles() is called.
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "one_to_many",
            joinColumns = @JoinColumn(name = "user_pk"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_pk"))
    private Collection<Vehicle> vehicles = new ArrayList<Vehicle>();

    // test bi directions, i.e. @ManyToOne from the other side
    @OneToMany
    @JoinTable(name = "one_to_many_to_one",
            joinColumns = @JoinColumn(name = "user_pk"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_pk"))
    private Collection<VehicleManyToOneUser> vehicleManyToOneUser = new ArrayList<VehicleManyToOneUser>();

    // test bi directions, i.e. @ManyToOne from the other side, but save the intermidiate table.
    @OneToMany(mappedBy = "userOneToManyVehicle")
    private Collection<VehicleManyToOneUser> vehicleManyToOneUserSimple = new ArrayList<VehicleManyToOneUser>();

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

    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }

    public Collection<VehicleManyToOneUser> getVehicleManyToOneUser() {
        return vehicleManyToOneUser;
    }

    public Collection<VehicleManyToOneUser> getVehicleManyToOneUserSimple() {
        return vehicleManyToOneUserSimple;
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

    public void setVehicles(Collection<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setVehicleManyToOneUser(Collection<VehicleManyToOneUser> vehicleManyToOneUser) {
        this.vehicleManyToOneUser = vehicleManyToOneUser;
    }

    public void setVehicleManyToOneUserSimple(Collection<VehicleManyToOneUser> vehicleManyToOneUserSimple) {
        this.vehicleManyToOneUserSimple = vehicleManyToOneUserSimple;
    }
}
