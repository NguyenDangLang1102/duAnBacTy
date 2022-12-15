package com.example.demo_get.mapper;

import com.example.demo_get.model.dto.AccountDto;
import com.example.demo_get.model.entity.AccountEntity;

public class AccountMapper {
    public static AccountDto mapEntity(AccountEntity accountEntity){
        AccountDto accountDto =new AccountDto();
        accountDto.setIdBot(accountEntity.getIdBot());
        accountDto.setBotPrice(accountEntity.getBotPrice());
        accountDto.setRole(accountEntity.getRole());
        accountDto.setNameUser(accountEntity.getNameUser());
        return accountDto;
    }
}
