package com.work.orders.services;

import com.work.orders.models.Customers;
import com.work.orders.repositories.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerservices")
public class CustomerServiceImpl implements CustomerServices {

    @Autowired
    CustomersRepo cstrepo;

    @Transactional
    @Override
    public Customers save(Customers customers) {
        return cstrepo.save(customers);
    }

    @Override
    public List<Customers> findAllCustomers() {
        List<Customers> list = new ArrayList<>();
        cstrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customers findCustomerById(long id) {
        Customers rtnGet = cstrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
        return rtnGet ;
    }

    @Override
    public List<Customers> findCustomersByLikeName(String subname) {
        List<Customers> rtnList = cstrepo.findByCustnameContainingIgnoringCase(subname);
        return rtnList;
    }
}
