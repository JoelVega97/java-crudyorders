package com.work.orders.repositories;

import com.work.orders.models.Orders;
import org.hibernate.criterion.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Orders, Long> {
}
