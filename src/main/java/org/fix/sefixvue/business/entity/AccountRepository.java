package org.fix.sefixvue.business.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // 거래처명 조회
    @Query("SELECT a FROM Account a WHERE a.accountname = :searchName")
    List<Account> findByAccountNameContaining(@Param("searchName") String searchName);
}
