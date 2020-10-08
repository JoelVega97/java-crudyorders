package com.work.orders.controllers;


import com.work.orders.models.Customers;
import com.work.orders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    //POST http://localhost:2019/customers/customer
    @PostMapping(value = "/customer",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity addCustomer(@Valid @RequestBody Customers newCustomer){
        newCustomer.setCustcode(0);
        newCustomer = cstmrsrvcs.save(newCustomer);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{restid}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newRestaurantURI);
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    //PUT http://localhost:2019/customers/customer/19
    @PutMapping(value = "/customer/{custid}",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<?> replaceCustomerById(
            @PathVariable long custid,
            @Valid
            @RequestBody Customers updatedCustomer){
        updatedCustomer.setCustcode(custid);
        updatedCustomer = cstmrsrvcs.save(updatedCustomer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //PATCH http://localhost:2019/customers/customer/19
    @PatchMapping(value = "/customer/{custid}", consumes = {"application/json"})
    public ResponseEntity<?> updateCustomerById(@PathVariable long custid, @RequestBody Customers updateCustomer){
        cstmrsrvcs.update(updateCustomer, custid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DELETE http://localhost:2019/customers/customer/54
    @DeleteMapping(value = "/customer/{custid}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custid){
        cstmrsrvcs.delete(custid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
