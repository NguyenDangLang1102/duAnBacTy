package com.example.demo_get.service;


import com.example.demo_get.model.in.BotIn;
import com.example.demo_get.model.respond.CommandRespond;
import org.springframework.stereotype.Service;

@Service
public interface BotService {

    CommandRespond insert(BotIn botIn);
    CommandRespond commandBot ();




}
