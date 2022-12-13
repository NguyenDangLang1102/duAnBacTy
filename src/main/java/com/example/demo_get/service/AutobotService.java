package com.example.demo_get.service;

import com.example.demo_get.model.in.AutoBotIn;
import com.example.demo_get.model.in.CreatBotIn;
import com.example.demo_get.model.respond.CommandRespond;
import org.springframework.stereotype.Service;


@Service
public interface AutobotService {
    CommandRespond insert(AutoBotIn autoBotIn);
    CommandRespond creatBot(CreatBotIn creatBotIn);

    CommandRespond commandAutoBot ();




}
