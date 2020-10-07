package com.work.orders.repositories;

import com.work.orders.models.Customers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepo extends CrudRepository<Customers, Long> {

   List<Customers> findByCustnameContainingIgnoringCase(String likename);
}
