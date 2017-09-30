package com.testing.hibernate.entity;

import com.testing.hibernate.entity.valueobject.Address;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by davidqi on 9/27/17.
 */
@Entity
@Table(name = "user_embeddable_address")
public class UserEmbeddableAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dob;
    @Transient
    private String hobbie;
    /**
     * Using columns in the same table, but change column name
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "state", column = @Column(name = "home_address_state")),
            @AttributeOverride(name = "city", column = @Column(name = "home_address_city")),
            @AttributeOverride(name = "street", column = @Column(name = "home_address_street"))
    })
    private Address homeAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "state", column = @Column(name = "office_address_state")),
            @AttributeOverride(name = "city", column = @Column(name = "office_address_city")),
            @AttributeOverride(name = "street", column = @Column(name = "office_address_street"))
    })
    private Address officeAddress;

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

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Address getOfficeAddress() {
        return officeAddress;
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

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
    }
}
