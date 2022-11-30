package com.example.demo_get.service;


import com.example.demo_get.model.respond.UserRespond;
import org.springframework.stereotype.Service;

@Service
public interface BotService {
    UserRespond commandBot ();
}
