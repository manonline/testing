package com.testing.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by davidqi on 9/27/17.
 */
@Entity
@Table(name = "vehicle_many_to_many_user")
public class VehicleManyToManyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String vehicleName;

    @ManyToMany(mappedBy = "vehicleManyToManyUser")
    private Collection<UserManyToManyVehicle> UserManyToManyVehicles = new ArrayList<UserManyToManyVehicle>();

    public int getId() {
        return id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public Collection<UserManyToManyVehicle> getUserManyToManyVehicles() {
        return UserManyToManyVehicles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setUserManyToManyVehicles(Collection<UserManyToManyVehicle> userManyToManyVehicles) {
        UserManyToManyVehicles = userManyToManyVehicles;
    }
}
