package org.fix.sefixvue.hrm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.SearchCondition;

import org.fix.sefixvue.hrm.entity.Attendence;
import org.fix.sefixvue.hrm.entity.Employee;
import org.fix.sefixvue.hrm.entity.EmployeeRepository;
import org.fix.sefixvue.hrm.model.dto.AttendenceDto;
import org.fix.sefixvue.hrm.model.dto.EmployeeDto;
import org.fix.sefixvue.hrm.model.service.HrmService;
import org.fix.sefixvue.jwt.JwtTokenProvider;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class HrmController {
    private final HrmService hrmService;
    private final EmployeeRepository employeeRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public String login(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findByEmpId(employeeDto.getEmpId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다."));

        return jwtTokenProvider.createToken(employee.getEmpId(), employee.getEmplevel());
    }
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

    @GetMapping("/hrm/employeeinfo/{empId}")
    public EmployeeDto getEmployeeInfo(@PathVariable String empId) {
        return hrmService.getEmployeeInfo(empId);
    }
    //근태관리
    @PostMapping("/hrm/adin")
    public Attendence createAttendence(@RequestBody AttendenceDto attendenceDto){
        return hrmService.createAttendence(attendenceDto);
    }
    @PatchMapping("/hrm/adout")
    public void updateEmployee(@RequestBody AttendenceDto attendenceDto) {
        hrmService.update(attendenceDto);
    }
    @GetMapping("/hrm/adinfo/{empId}")
    public AttendenceDto checkInfo(@PathVariable String empId){
        return hrmService.checkInfo(empId);
    }
    @PostMapping("/hrm/orderin")
    public Attendence createOrder(@RequestBody AttendenceDto attendenceDto){
        return hrmService.createOrder(attendenceDto);
    }
    @PatchMapping("/hrm/orderup")
    public void updateOrder(@RequestBody AttendenceDto attendenceDto) {
        hrmService.updateOrder(attendenceDto);
    }
}
