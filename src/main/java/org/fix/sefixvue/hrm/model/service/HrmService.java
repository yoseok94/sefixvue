package org.fix.sefixvue.hrm.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.hrm.entity.AttendenceRepository;
import org.fix.sefixvue.hrm.entity.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class HrmService {
    private final EmployeeRepository employeeRepository;
    private final AttendenceRepository attendenceRepository;
}
