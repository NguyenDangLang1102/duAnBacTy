package com.example.demo_get.service;


import com.example.demo_get.model.in.BuffBotPrice;
import com.example.demo_get.model.in.CreatBotIn;
import com.example.demo_get.model.respond.CommandRespond;
import org.springframework.stereotype.Service;

@Service
public interface AccountBotService {

    CommandRespond createBot(CreatBotIn creatBotIn);

    CommandRespond updateUser (Integer Id, BuffBotPrice buffBotPrice);
}
