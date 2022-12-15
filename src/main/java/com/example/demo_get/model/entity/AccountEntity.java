package com.example.demo_get.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ACCOUNT")
public class AccountEntity {

    @Id
    @Column(name = "ID")
    private  Integer idBot;

    @Column(name="NAMEUSER")
    private String nameUser;

    @Column(name = "BOTPRICE")
    private  Integer botPrice ;


    @Column(name="ROLE")
    private String role;
}
