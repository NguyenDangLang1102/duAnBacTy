package com.example.demo_get.repostory;

import com.example.demo_get.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <UserEntity,Integer>{

    @Procedure(procedureName = "PKG_COMMAND.GET_COMMAND",refCursor = true)
    List<UserEntity> getCommand();
    @Modifying
    @Procedure(name = "command.getbyid")
    UserEntity getbyid(@Param("p_ID") Integer p_ID);
    @Modifying
    @Query(value = "{call PKG_COMMAND.INSERT_COMMAND(?1,?2,?3,?4,?5) }",nativeQuery = true)
    void insertCommand( String nameUser,String nameStock,Boolean isSale, Integer stockPrice, Integer stockNumber);

    @Modifying
    @Transactional
    @Query(value = "{call PKG_COMMAND_COMPLETE.INSERT_COMMAND_COMPLETE(?1,?2,?3,?4,?5,?6,?7,?8) }",nativeQuery = true)
    void insertCommandComplete(String nameUser, String nameStock, Boolean isSale, Integer stockPrice, Integer realStockPrice, Integer stockNumber, Integer realStockNumber, Timestamp timeCreate);

    @Modifying
    @Transactional
    @Query(value = "{call PKG_COMMAND.DELETE_COMMAND(?1)}",nativeQuery = true)
    void deleteTable(Integer Id);

    @Modifying
    @Transactional
    @Query(value = "{call PKG_COMMAND.UPDATE_COMMAND(?1, ?2 )}",nativeQuery = true)
    void updateTable( Integer id, Integer stockNumber);

    UserEntity getById(Integer id);
}

