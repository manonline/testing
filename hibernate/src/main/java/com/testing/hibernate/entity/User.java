package com.testing.hibernate.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by davidqi on 9/25/17.
 */
@Entity(name = "UserEntity")
@Table(name = "user_info")
@Cacheable
// READ_ONLY: assume application only read the data from database
// NONSTRICTLY_READ_WRITE:
// READ_WRITE:
// TRANSACTIONAL:
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
@NamedQuery(name = "UserEntity.byId", query = "from UserEntity where id = :userId")
@NamedNativeQuery(name = "UserEntity.byName", query = "select * from user_info where name = :name", resultClass = User.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dob;
    @Transient
    private String hobbie;

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
}
