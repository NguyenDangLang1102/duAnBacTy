package com.example.demo_get.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Integer idBot;
    private Integer botPrice;
    private String nameUser;
    private String role;

}
