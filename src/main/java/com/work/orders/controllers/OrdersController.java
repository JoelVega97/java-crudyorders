package com.work.orders.controllers;

import com.work.orders.models.Orders;
import com.work.orders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrderServices ordrsrvc;

    //http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{orderid}", produces = {"application/json"})
    public ResponseEntity<?> findOrderById(@PathVariable long orderid){
        Orders rtnGet = ordrsrvc.findOrderById(orderid);
        return new ResponseEntity<>(rtnGet, HttpStatus.OK);
    }

}
