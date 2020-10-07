package com.work.orders.services;

import com.work.orders.models.Agents;
import com.work.orders.models.Customers;
import com.work.orders.repositories.AgentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "agentservices")
public class AgentServiceImpl implements AgentServices {

    @Autowired
    AgentsRepo agntrepo;

    @Transactional

    @Override
    public Agents save(Agents agents) {
        return agntrepo.save(agents);
    }

    @Override
    public Agents findAgentById(long id) {
        Agents rtnGet = agntrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent "+ id +" not found"));
        return rtnGet;
    }
}
