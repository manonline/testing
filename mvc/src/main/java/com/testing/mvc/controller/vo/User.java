package com.testing.mvc.controller.vo;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private String name;
    private String hobby;
    private String mobile;
    private Date dob;
    private ArrayList<String> skills = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public String getHobby() {
        return hobby;
    }

    public String getMobile() {
        return mobile;
    }

    public Date getDob() {
        return dob;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }
}
