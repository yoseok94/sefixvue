package org.fix.sefixvue.hrm.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee> findByEmpId(String empId);
}
