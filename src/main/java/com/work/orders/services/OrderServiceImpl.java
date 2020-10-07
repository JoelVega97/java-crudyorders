package com.work.orders.services;

import com.work.orders.models.Orders;
import com.work.orders.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "orderservices")

public class OrderServiceImpl implements OrderServices {

    @Autowired
    OrderRepo ordrepo;

    @Transactional
    @Override
    public Orders save(Orders orders) {
        return ordrepo.save(orders);
    }

    @Override
    public Orders findOrderById(long orderid) {
        Orders rtnGet = ordrepo.findById(orderid)
                .orElseThrow(() -> new EntityNotFoundException("Order "+ orderid +" not found" ));
        return rtnGet;
    }
}
