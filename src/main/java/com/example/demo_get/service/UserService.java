package com.example.demo_get.service;

import com.example.demo_get.model.dto.UserDto;
import com.example.demo_get.model.entity.UserEntity;
import com.example.demo_get.model.in.UserIn;
import com.example.demo_get.model.respond.UserRespond;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service

public interface UserService {
    UserRespond getAll();
    UserRespond create(UserIn userIn);
    UserRespond delete(Integer Id);
    UserRespond update(Integer Id , UserIn userIn);
    UserRespond getById(Integer p_ID);

}
