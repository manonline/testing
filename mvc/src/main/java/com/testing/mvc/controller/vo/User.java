package com.testing.mvc.controller.vo;

import java.util.Date;

public class User {
    private String name;
    private Date dob;
    private String mobile;
    private String hobbie;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setHobbie(String hobbie) {
        this.hobbie = hobbie;
    }

    public String getHobbie() {

        return hobbie;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
