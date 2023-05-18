package org.fix.sefixvue.hrm.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmpId(String empId);
}
