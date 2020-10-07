package com.work.orders.repositories;

import com.work.orders.models.Payments;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepo extends CrudRepository<Payments, Long> {
}
