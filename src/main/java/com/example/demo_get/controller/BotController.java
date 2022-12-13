package com.example.demo_get.controller;


import com.example.demo_get.model.in.BotIn;
import com.example.demo_get.service.BotService;
import com.example.demo_get.service.implement.BotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("bot")
@Transactional
public class BotController {

    private static final String SCHEDULED_TASKS = "scheduledTasks";

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;//import

    @Autowired
    private BotServiceImpl botServiceImpl;

    @Autowired
    private BotService botService;


    @GetMapping("/stopScheduler")
    public String stopSchedule(){
        postProcessor.postProcessBeforeDestruction( botServiceImpl,SCHEDULED_TASKS );
        return "stop bot";
    }
    @GetMapping(value = "/startScheduler")
    public String startSchedule() {
        postProcessor.postProcessAfterInitialization(botServiceImpl, SCHEDULED_TASKS);
        return "start bot";
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody BotIn botIn) {
        return new ResponseEntity<>(botService.insert(botIn), HttpStatus.CREATED);
    }
}
