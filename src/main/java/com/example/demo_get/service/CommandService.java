package com.example.demo_get.service;

import com.example.demo_get.model.in.CommandIn;
import com.example.demo_get.model.respond.CommandRespond;
import org.springframework.stereotype.Service;

@Service

public interface CommandService {
    CommandRespond getAll();
    CommandRespond create(CommandIn commandIn);
    CommandRespond delete(Integer Id);
    CommandRespond update(Integer Id , CommandIn commandIn);
    CommandRespond getById(Integer p_ID);

}
