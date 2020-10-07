package com.work.orders.controllers;


import com.work.orders.models.Customers;
import com.work.orders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerServices cstmrsrvcs;

    //http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers(){
        List<Customers> rtnList = cstmrsrvcs.findAllCustomers();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    //http://localhost:2019/customers/customer/7
    @GetMapping(value = "/customer/{custid}", produces = {"application/json"})
    public ResponseEntity<?> findCustomerById(@PathVariable long custid){
        Customers rtnGet = cstmrsrvcs.findCustomerById(custid);
        return new ResponseEntity<>(rtnGet, HttpStatus.OK);
    }
    //http://localhost:2019/customers/customer/77 ERR

    //http://localhost:2019/customers/namelike/mes
    @GetMapping(value = "/namelike/{subname}", produces = {"application/json"})
    public ResponseEntity<?> findCustomerLikeName(@PathVariable String subname){
        List<Customers> rtnList = cstmrsrvcs.findCustomersByLikeName(subname);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }
    //http://localhost:2019/customers/namelike/cin EMPTY ARR


}
