package com.work.orders.services;

import com.work.orders.models.Customers;

import java.util.List;

public interface CustomerServices {

    Customers save(Customers customers);

    List<Customers> findAllCustomers();

    Customers findCustomerById(long id);

    List<Customers> findCustomersByLikeName(String subname);

    void delete(long id);
}
