package com.work.orders.services;

import com.work.orders.models.Payments;
import com.work.orders.repositories.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

    @Transactional
    @Service(value = "paymentservices")
public class PaymentServicesImpl implements PaymentServices{

        @Autowired
        PaymentRepo agntrepos;

        @Transactional
        @Override
        public Payments save(Payments payment){return agntrepos.save(payment);}
    }
