package com.testing.hibernate.entity;

import com.testing.hibernate.entity.valueobject.Address;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by davidqi on 9/27/17.
 */
@Entity
@Table(name = "user_collection_address")
public class UserCollectionAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dob;
    @Transient
    private String hobbie;

    /**
     * Another table to hold embedded Addresses.
     */
    @ElementCollection
    // name of the table and associated joint column
    @JoinTable(name = "address_for_user", joinColumns = @JoinColumn(name = "user_id"))
    @GenericGenerator(name = "hilo-gen", strategy = "hilo")
    // adding a PK for the address record in the new table
    @CollectionId(columns = {@Column(name = "address_id")}, generator = "hilo-gen", type = @Type(type = "long"))
    private Collection<Address> address = new ArrayList<Address>();

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

    public Collection<Address> getAddress() {
        return address;
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

    public void setAddress(Collection<Address> address) {
        this.address = address;
    }
}
