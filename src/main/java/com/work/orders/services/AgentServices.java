package com.work.orders.services;

import com.work.orders.models.Agents;

public interface AgentServices {

    Agents save(Agents agents);

    Agents findAgentById(long id);
}
