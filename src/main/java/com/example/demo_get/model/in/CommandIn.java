package com.example.demo_get.model.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CommandIn {
    private String nameUser;
    private  String nameStock;
    private  Integer stockPrice;
    private  Boolean isSale;
    private Integer stockNumber;

}
