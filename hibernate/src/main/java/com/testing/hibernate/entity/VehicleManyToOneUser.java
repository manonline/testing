package com.testing.hibernate.entity;

import javax.persistence.*;

/**
 * Created by davidqi on 9/27/17.
 */
@Entity
@Table(name = "vehicle_many_to_one_user")
public class VehicleManyToOneUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String vehicleName;

    @ManyToOne
    @JoinColumn(name = "user_pk")
    //@NotFound(action= NotFoundAction.IGNORE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_to_many_pk")
    private UserOneToManyVehicle userOneToManyVehicle;

    public int getId() {
        return id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public User getUser() {
        return user;
    }

    public UserOneToManyVehicle getUserOneToManyVehicle() {
        return userOneToManyVehicle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserOneToManyVehicle(UserOneToManyVehicle userOneToManyVehicle) {
        this.userOneToManyVehicle = userOneToManyVehicle;
    }
}
