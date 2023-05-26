package org.fix.sefixvue.accounting.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AllowancesDeductionsRepository extends JpaRepository<AllowancesDeductions, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM AllowancesDeductions ad WHERE ad.adcode = ?1")
    void deleteByAdcode(String adcode);
}
