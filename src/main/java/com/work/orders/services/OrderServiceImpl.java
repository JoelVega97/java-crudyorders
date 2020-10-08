package com.work.orders.services;

import com.work.orders.models.Customers;
import com.work.orders.models.Orders;
import com.work.orders.models.Payments;
import com.work.orders.repositories.CustomersRepo;
import com.work.orders.repositories.OrderRepo;
import com.work.orders.repositories.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "orderservices")

public class OrderServiceImpl implements OrderServices {

    @Autowired
    OrderRepo ordrepo;

    @Autowired
    PaymentRepo pmntrepo;

    @Autowired
    CustomersRepo custrepo;

    @Transactional
    @Override
    public Orders save(Orders orders) {
        Orders newOrder = new Orders();

        if(orders.getOrdnum() != 0){
            ordrepo.findById(orders.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + orders.getOrdnum() + " does not exist"));
            newOrder.setOrdnum(orders.getOrdnum());
        }

        newOrder.setOrdamount(orders.getOrdamount());
        newOrder.setAdvanceamount(orders.getAdvanceamount());
        newOrder.setOrderdescription(orders.getOrderdescription());

        newOrder.setCustomer(custrepo.findById(orders.getCustomer().getCustcode())
                .orElseThrow(() -> new EntityNotFoundException("Customer " + orders.getCustomer().getCustcode() + " Not Found!")));

        newOrder.getPayments().clear();
        for(Payments p : orders.getPayments()){
            Payments newPay = pmntrepo.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment "+ p.getPaymentid() +" Not Found"));
            newOrder.getPayments().add(newPay);
        }
        return ordrepo.save(newOrder);

    }

    @Override
    public Orders findOrderById(long orderid) {
        Orders rtnGet = ordrepo.findById(orderid)
                .orElseThrow(() -> new EntityNotFoundException("Order "+ orderid +" not found" ));
        return rtnGet;
    }

    @Override
    public void delete(long ordnum) {
        if(ordrepo.findById(ordnum).isPresent()){
            ordrepo.deleteById(ordnum);
        }else{
            throw new EntityNotFoundException("Order " + ordnum +" Not Found!");
        }
    }
}
