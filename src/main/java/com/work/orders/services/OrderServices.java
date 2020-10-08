package com.work.orders.services;

import com.work.orders.models.Orders;

public interface OrderServices {

    Orders save(Orders orders);

    Orders findOrderById(long orderid);

    void delete(long ordnum);
}
