package org.fix.sefixvue.admin.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Dept, Long> {

    Page<Dept> findAllByOrderByDeptnoDesc(Pageable pageable);
}
