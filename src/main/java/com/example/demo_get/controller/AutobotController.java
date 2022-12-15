package com.example.demo_get.controller;


import com.example.demo_get.model.in.AutoBotIn;
import com.example.demo_get.model.in.BotIn;
import com.example.demo_get.model.in.CreatBotIn;
import com.example.demo_get.service.AutobotService;
import com.example.demo_get.service.implement.AutoBotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("autoBot")
@Transactional
public class AutobotController {
    private static final String SCHEDULED_TASKS = "scheduledTasks";

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;//import
    @Autowired
    private AutoBotServiceImpl autoBotServiceimpl;
    @Autowired
    private  AutobotService autobotService;

    @GetMapping("/stopScheduler")
    public String stopSchedule(){
        postProcessor.postProcessBeforeDestruction( autoBotServiceimpl,SCHEDULED_TASKS );
        return "stop auto bot";
    }
    @GetMapping(value = "/startScheduler")
    public String startSchedule() {
        postProcessor.postProcessAfterInitialization(autoBotServiceimpl, SCHEDULED_TASKS);
        return  "start auto bot" ;
    }
    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody AutoBotIn autoBotIn) {
        return new ResponseEntity<>(autobotService.insert(autoBotIn), HttpStatus.CREATED);
    }



}
