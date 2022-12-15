package com.example.demo_get.controller;


import com.example.demo_get.model.in.BuffBotPrice;
import com.example.demo_get.model.in.CreatBotIn;
import com.example.demo_get.service.AccountBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("accountBot")
@Transactional
public class AccountBotController {
    @Autowired
    private AccountBotService accountBotService;



    @PostMapping("/creat")
    public ResponseEntity<?> creatBot(@RequestBody CreatBotIn creatBotIn) {
        return new ResponseEntity<>(accountBotService.createBot(creatBotIn), HttpStatus.CREATED);
    }

    @PutMapping("/buff/{Id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer Id, @RequestBody BuffBotPrice buffBotPrice){
        return new ResponseEntity<>(accountBotService.updateUser(Id,buffBotPrice),HttpStatus.OK);
    }
}
