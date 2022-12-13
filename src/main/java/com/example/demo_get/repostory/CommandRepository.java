package com.example.demo_get.repostory;

import com.example.demo_get.model.entity.CommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository <CommandEntity,Integer>{

    @Procedure(procedureName = "PKG_COMMAND.GET_COMMAND",refCursor = true)
    List<CommandEntity> getCommand();

    @Modifying
    @Procedure(name = "command.getbyid")
    CommandEntity getbyid(@Param("p_ID") Integer p_ID);
    @Modifying
    @Query(value = "{call PKG_COMMAND.INSERT_COMMAND(?1,?2,?3,?4,?5) }",nativeQuery = true)
    void insertCommand( String nameUser,String nameStock,Boolean isSale, Integer stockPrice, Integer stockNumber);

    @Modifying
    @Transactional
    @Query(value = "{call PKG_COMMAND.INSERT_COMMAND_COMPLETE(?1,?2,?3,?4,?5,?6,?7,?8) }",nativeQuery = true)
    void insertCommandComplete(String nameUser, String nameStock, Boolean isSale, Integer stockPrice, Integer realStockPrice, Integer stockNumber, Integer realStockNumber, Timestamp timeCreate);

    @Modifying
    @Transactional
    @Query(value = "{call PKG_COMMAND.DELETE_COMMAND(?1)}",nativeQuery = true)
    void deleteTable(Integer Id);

    @Modifying
    @Transactional
    @Query(value = "{call PKG_COMMAND.UPDATE_COMMAND(?1, ?2 )}",nativeQuery = true)
    void updateTable( Integer id, Integer stockNumber);

    CommandEntity getById(Integer id);
}

