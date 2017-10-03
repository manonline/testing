package com.testing.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String ordNum;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Collection<OrderLine> ordLines = new ArrayList<OrderLine>();

    public int getId() {
        return id;
    }

    public String getOrdNum() {
        return ordNum;
    }

    public Collection<OrderLine> getOrdLines() {
        return ordLines;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrdNum(String ordNum) {
        this.ordNum = ordNum;
    }

    public void setOrdLines(Collection<OrderLine> ordLines) {
        this.ordLines = ordLines;
    }
}
