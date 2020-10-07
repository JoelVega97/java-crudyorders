package com.work.orders.controllers;

import com.work.orders.models.Agents;
import com.work.orders.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentsController {
    @Autowired
    AgentServices agntsrvcs;

    //http://localhost:2019/agents/agent/9
    @GetMapping(value = "/agent/{agentid}", produces = {"application/json"})
    public ResponseEntity<?> findAgentById(@PathVariable long agentid){
        Agents rtnGet = agntsrvcs.findAgentById(agentid);
        return new ResponseEntity<>(rtnGet, HttpStatus.OK);
    }
}
