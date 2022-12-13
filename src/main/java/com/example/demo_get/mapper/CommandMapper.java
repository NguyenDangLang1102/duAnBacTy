package com.example.demo_get.mapper;

import com.example.demo_get.model.dto.CommandDto;
import com.example.demo_get.model.entity.CommandEntity;
import com.example.demo_get.model.in.CommandIn;

public class CommandMapper {
    public static CommandEntity mapIn(CommandIn commandIn){
        CommandEntity commandEntity = new CommandEntity();
        commandEntity.setNameUser(commandIn.getNameUser());
        commandEntity.setNameStock(commandIn.getNameStock());
        commandEntity.setStockPrice(commandIn.getStockPrice());
        commandEntity.setIsSale(commandIn.getIsSale());
        commandEntity.setStockNumber(commandIn.getStockNumber());
        return commandEntity;
    }

    public static CommandDto mapEntity(CommandEntity commandEntity){
        CommandDto commandDto =new CommandDto();
        commandDto.setId(commandEntity.getId());
        commandDto.setNameUser(commandEntity.getNameUser());
        commandDto.setStockPrice(commandEntity.getStockPrice());
        commandDto.setNameStock(commandEntity.getNameStock());
        commandDto.setIsSale(commandEntity.getIsSale());
        commandDto.setStockNumber(commandEntity.getStockNumber());
        commandDto.setTimeCreate(commandEntity.getTimeCreate());
        return commandDto;
    }


}
