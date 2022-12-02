package com.example.demo_get.controller;

import com.example.demo_get.model.in.BotIn;
import com.example.demo_get.model.in.UserIn;
import com.example.demo_get.service.BotService;
import com.example.demo_get.service.UserService;
import com.example.demo_get.service.implement.BotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("")
@Transactional
public class ControllerUser {

    @Autowired
    private BotServiceImpl botServiceImpl;
    @Autowired
    private UserService userService;



    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getById(@RequestParam Integer p_ID) {
        return new ResponseEntity<>(userService.getById(p_ID), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody UserIn userIn) {
        return new ResponseEntity<>(userService.create(userIn), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?> delete(@PathVariable Integer Id) {
        return new ResponseEntity<>(userService.delete(Id), HttpStatus.OK);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<?> update(@RequestBody UserIn userIn, @PathVariable Integer Id) {
        return new ResponseEntity<>(userService.update(Id, userIn), HttpStatus.OK);
    }
}

