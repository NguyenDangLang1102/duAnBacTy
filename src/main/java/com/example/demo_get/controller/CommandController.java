package com.example.demo_get.controller;

import com.example.demo_get.model.in.CommandIn;
import com.example.demo_get.service.CommandService;
import com.example.demo_get.service.implement.BotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("")
@Transactional
public class CommandController {

    @Autowired
    private BotServiceImpl botServiceImpl;
    @Autowired
    private CommandService commandService;



    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(commandService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getById(@RequestParam Integer p_ID) {
        return new ResponseEntity<>(commandService.getById(p_ID), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CommandIn commandIn) {
        return new ResponseEntity<>(commandService.create(commandIn), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?> delete(@PathVariable Integer Id) {
        return new ResponseEntity<>(commandService.delete(Id), HttpStatus.OK);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<?> update(@RequestBody CommandIn commandIn, @PathVariable Integer Id) {
        return new ResponseEntity<>(commandService.update(Id, commandIn), HttpStatus.OK);
    }
}

