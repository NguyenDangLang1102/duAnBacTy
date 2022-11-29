package com.example.demo_get.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
private Integer Id;
private String nameUser;
private  String nameStock;
private  Integer stockPrice;
private  Boolean isSale;
private Integer stockNumber;
private Timestamp timeCreate;
}
