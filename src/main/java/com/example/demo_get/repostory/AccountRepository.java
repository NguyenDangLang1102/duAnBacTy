package com.example.demo_get.repostory;

import com.example.demo_get.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    @Procedure(procedureName = "PKG_ACCOUNT.GET_ACCOUNT",refCursor = true)
    List<AccountEntity> getAccount();

    @Modifying
    @Query(value = "{call PKG_ACCOUNT.INSERT_ACCOUNT(?1,?2,?3) }",nativeQuery = true)
    void insertAccount( String nameUser,String botPrice, String role);

    @Modifying
    @Transactional
    @Query (value = "{call PKG_ACCOUNT.UPDATE_ACCOUNT(?1,?2)}",nativeQuery = true)
    void updateUser(Integer id, String botPrice);
}
