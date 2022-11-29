package com.example.demo_get.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="COMMAND")
@NamedStoredProcedureQuery(name = "command.getbyid",
        resultClasses = UserEntity.class,
        procedureName = "PKG_COMMAND_COMPLETE.GET_BY_ID",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN,type = Integer.class,name = "p_ID"),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR,type = ResultSet.class,name = "V_CURSOR")
        }
)


public class UserEntity {
    @Id
    @Column(name = "ID")
    private  Integer Id;

    @Column(name = "ISSALE")
    private  Boolean isSale ;

    @Column(name="NAMEUSER")
    private String nameUser;

    @Column(name = "NAMESTOCK")
    private  String nameStock ;

    @Column(name = "STOCKPRICE")
    private  Integer stockPrice ;

    @Column(name= "STOCKNUMBER")
    private Integer stockNumber;

    @Column(name = "TIMECREATE")
    private Timestamp timeCreate;

}
