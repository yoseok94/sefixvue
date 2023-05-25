package org.fix.sefixvue.hrm.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface AttendenceRepository extends JpaRepository<Attendence, Long> {
    List<Attendence> findByEmpId(String empId);
}
