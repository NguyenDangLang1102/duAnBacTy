package com.example.demo_get.model.in;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BotIn {

    private Integer ratioSell ;
    private Integer timeSleep;
    private  Integer desirStockPrice;
    private Integer times;



}
