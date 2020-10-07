package com.work.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long ordnum;

    private double ordamount;

    private double advanceamount;

    private String orderdescription;

    //custcode MTO
    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    @JsonIgnoreProperties(value = "ordersList", allowSetters = true)
    private Customers customer;

    //payments MTM
    @ManyToMany
    @JoinTable(name = "orderspayments",
            joinColumns = @JoinColumn(name = "ordnum"),
            inverseJoinColumns = @JoinColumn(name = "paymentid"))
    @JsonIgnoreProperties(value = "orders", allowSetters = true)
    private Set<Payments> payments = new HashSet<>();

    public Orders() {
    }

    public Orders(double ordamount,
                  double advanceamount,
                  Customers customer,
                  String orderdescription)
    {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.customer = customer;
        this.orderdescription = orderdescription;
    }

    public long getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(long ordernum) {
        this.ordnum = ordernum;
    }

    public double getOrdamount() {
        return ordamount;
    }

    public void setOrdamount(double ordamount) {
        this.ordamount = ordamount;
    }

    public double getAdvanceamount() {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount) {
        this.advanceamount = advanceamount;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Set<Payments> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payments> payments) {
        this.payments = payments;
    }

    public String getOrderdescription() {
        return orderdescription;
    }

    public void setOrderdescription(String orderdesc) {
        this.orderdescription = orderdesc;
    }
}
