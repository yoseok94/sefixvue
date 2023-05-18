package org.fix.sefixvue.hrm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.SearchCondition;
import org.fix.sefixvue.common.security.domain.AuthenticationRequest;
import org.fix.sefixvue.hrm.entity.Employee;
import org.fix.sefixvue.hrm.model.dto.EmployeeDto;
import org.fix.sefixvue.hrm.model.service.HrmService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class HrmController {
    private final HrmService hrmService;

    @GetMapping("/hrm/hrmmember")
    public Header<List<EmployeeDto>> employeeList(
            @PageableDefault(sort = {"empno"}) Pageable pageable,
            SearchCondition searchCondition) {
        return hrmService.getEmployeeList(pageable, searchCondition);
    }
    @PatchMapping("/hrm/employeequit")
    public Employee employeeQuit(@RequestBody EmployeeDto employeeDto){
        return hrmService.updateEmpStatus(employeeDto);
    }
    @GetMapping("/hrm/hrmup/{empno}")
    public EmployeeDto getEmployee(@PathVariable Long empno){
        return hrmService.getEmployee(empno);
    }
    @PatchMapping("/hrm/hrmup")
    public void updateEmployee(@RequestBody EmployeeDto employeeDto) {
        hrmService.update(employeeDto);
    }

}
