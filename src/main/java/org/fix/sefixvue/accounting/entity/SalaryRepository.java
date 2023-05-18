package org.fix.sefixvue.accounting.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long>, JpaSpecificationExecutor<Salary> {

    @Query("SELECT s FROM Salary s WHERE s.paymentdate >= :startOfDay AND s.paymentdate < :nextDay AND s.empid = :empid")
    Optional<Salary> findSalaryByTruncPaymentdateAndEmpid(@Param("startOfDay") java.util.Date startOfDay, @Param("nextDay") java.util.Date nextDay, @Param("empid") String empid);

}