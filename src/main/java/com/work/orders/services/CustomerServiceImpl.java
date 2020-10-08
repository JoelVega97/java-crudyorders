package com.work.orders.services;

import com.work.orders.models.Customers;
import com.work.orders.models.Orders;
import com.work.orders.models.Payments;
import com.work.orders.repositories.CustomersRepo;
import com.work.orders.repositories.PaymentRepo;
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

    @Autowired
    PaymentRepo pmntrepo;

    @Transactional
    @Override
    public Customers save(Customers customers) {
        Customers newCustomer = new Customers();
        if(customers.getCustcode() != 0){
            cstrepo.findById(customers.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customers.getCustcode() + " does not exist"));
            newCustomer.setCustcode(customers.getCustcode());
        }

        newCustomer.setCustname(customers.getCustname());
        newCustomer.setCustcity(customers.getCustcity());
        newCustomer.setWorkingarea(customers.getWorkingarea());
        newCustomer.setCustcountry(customers.getCustcountry());
        newCustomer.setGrade(customers.getGrade());
        newCustomer.setOpeningamt(customers.getOpeningamt());
        newCustomer.setReceiveamt(customers.getReceiveamt());
        newCustomer.setPaymentamt(customers.getPaymentamt());
        newCustomer.setOutstandingamt(customers.getOutstandingamt());
        newCustomer.setPhone(customers.getPhone());
        newCustomer.setAgent(customers.getAgent());

        newCustomer.getOrdersList().clear();
        for(Orders o : customers.getOrdersList()){
            Orders newOrder = new Orders();
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setOrderdescription(o.getOrderdescription());

            newOrder.getPayments().clear();
            for(Payments p : o.getPayments()){
                Payments newPay = pmntrepo.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment "+ p.getPaymentid() +" Not Found"));
                newOrder.getPayments().add(newPay);
            }
        }
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

    @Transactional
    @Override
    public void delete(long id) {
        if (cstrepo.findById(id).isPresent()){
            cstrepo.deleteById(id);
        }else{
            throw new EntityNotFoundException("Customer " + id +" Not Found!");
        }
    }

    @Override
    public Customers update(Customers customers, long id) {
        Customers updateCustomer = cstrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id +" Not Found!"));

        if(customers.getCustname() != null){
            updateCustomer.setCustname(customers.getCustname());
        }

        if(customers.getCustcity() != null){
            updateCustomer.setCustcity(customers.getCustcity());
        }

        if(customers.getWorkingarea() != null){
            updateCustomer.setWorkingarea(customers.getWorkingarea());
        }

        if(customers.getCustcountry() != null){
            updateCustomer.setCustcountry(customers.getCustcountry());
        }

        if(customers.getGrade() != null){
            updateCustomer.setGrade(customers.getGrade());
        }

        if(customers.hasvalueforopeningamt){
            updateCustomer.setOpeningamt(customers.getOpeningamt());
        }

        if(customers.hasvalueforreceiveamt){
            updateCustomer.setReceiveamt(customers.getReceiveamt());
        }

        if(customers.hasvalueforpaymentamt){
            updateCustomer.setPaymentamt(customers.getPaymentamt());
        }

        if(customers.hasvalueforoutstandingamt){
            updateCustomer.setOutstandingamt(customers.getOutstandingamt());
        }

        if(customers.getPhone() != null){
            updateCustomer.setPhone(customers.getPhone());
        }

        if(customers.getAgent() != null){
            updateCustomer.setAgent(customers.getAgent());
        }

        if(customers.getOrdersList().size() > 0){
        updateCustomer.getOrdersList().clear();
        for(Orders o : customers.getOrdersList()){
            Orders updateOrder = new Orders();
            updateOrder.setOrdamount(o.getOrdamount());
            updateOrder.setAdvanceamount(o.getAdvanceamount());
            updateOrder.setOrderdescription(o.getOrderdescription());

            if (updateOrder.getPayments().size() > 0){
            updateOrder.getPayments().clear();
            for(Payments p : o.getPayments()){
                Payments newPay = pmntrepo.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment "+ p.getPaymentid() +" Not Found"));
                updateOrder.getPayments().add(newPay);
            }

            }
        }

        }

        return cstrepo.save(customers);
    }

}
