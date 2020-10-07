package com.work.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long paymentid;

    @Column(nullable = false, unique = true)
    private String type;

    @ManyToMany(mappedBy = "payments")
    @JsonIgnoreProperties(value = "payments")
    private Set<Orders> orders = new HashSet<>();

    public Payments() {
    }

    public Payments(String type) {
        this.type = type;
    }

    public long getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(long paymentid) {
        this.paymentid = paymentid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
