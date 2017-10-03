package com.testing.hibernate.entity;

import javax.persistence.*;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Order order;

    private int lineNum;

    private String description;

    public int getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public int getLineNum() {
        return lineNum;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
