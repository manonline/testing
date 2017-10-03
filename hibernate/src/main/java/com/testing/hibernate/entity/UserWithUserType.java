package com.testing.hibernate.entity;

import com.testing.hibernate.entity.type.AddressType;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_with_user_type")
@TypeDef(name = "AddressType", typeClass = AddressType.class)
public class UserWithUserType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dob;
    @Transient
    private String hobbie;

    // @Type to specify the class (convertor) that knows how to serialize instances of another class to and from JDBC
    @Type(type = "AddressType")
    @Columns(columns = {
            @Column(name = "homeAddr"),
            @Column(name= "workAddr")})
    private AddressType addressType;

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

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public AddressType getAddressType() {

        return addressType;
    }
}
