package com.example.demo_get.mapper;

import com.example.demo_get.model.dto.UserDto;
import com.example.demo_get.model.entity.UserEntity;
import com.example.demo_get.model.in.UserIn;

public class MapperUser {
    public static UserEntity mapIn(UserIn userIn){
        UserEntity userEntity = new UserEntity();
        userEntity.setNameUser(userIn.getNameUser());
        userEntity.setNameStock(userIn.getNameStock());
        userEntity.setStockPrice(userIn.getStockPrice());
        userEntity.setIsSale(userIn.getIsSale());
        userEntity.setStockNumber(userIn.getStockNumber());
        return userEntity;
    }

    public static UserDto mapEntity(UserEntity userEntity){
        UserDto userDto =new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setNameUser(userEntity.getNameUser());
        userDto.setStockPrice(userEntity.getStockPrice());
        userDto.setNameStock(userEntity.getNameStock());
        userDto.setIsSale(userEntity.getIsSale());
        userDto.setStockNumber(userEntity.getStockNumber());
        userDto.setTimeCreate(userEntity.getTimeCreate());
        return userDto;
    }
}
