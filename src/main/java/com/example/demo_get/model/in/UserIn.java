package com.example.demo_get.model.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserIn {
    private String nameUser;
    private  String nameStock;
    private  Integer stockPrice;
    private  Boolean isSale;
    private Integer stockNumber;

}
