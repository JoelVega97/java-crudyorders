package com.work.orders.controllers;

import com.work.orders.models.Customers;
import com.work.orders.models.Orders;
import com.work.orders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    //POST http://localhost:2019/orders/order
    @PostMapping(value = "/order",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<?> addOrder(@Valid @RequestBody Orders newOrder){
        newOrder.setOrdnum(0);
        newOrder = ordrsrvc.save(newOrder);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ordnum}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();
        responseHeaders.setLocation(newRestaurantURI);
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    //PUT http://localhost:2019/orders/order/63
    @PutMapping(value = "/order/{ordnum}",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<?> replaceOrderById(
            @PathVariable long ordnum,
            @Valid
            @RequestBody Orders updatedCustomer){
        updatedCustomer.setOrdnum(ordnum);
        updatedCustomer = ordrsrvc.save(updatedCustomer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DELETE http://localhost:2019/orders/order/58
    @DeleteMapping(value = "/order/{ordnum}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long ordnum){
        ordrsrvc.delete(ordnum);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
