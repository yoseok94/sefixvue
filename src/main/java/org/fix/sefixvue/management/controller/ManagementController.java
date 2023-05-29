package org.fix.sefixvue.management.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.admin.entity.Dept;
import org.fix.sefixvue.admin.model.dto.DeptDto;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.SearchCondition;
import org.fix.sefixvue.hrm.entity.Employee;
import org.fix.sefixvue.hrm.model.dto.EmployeeDto;
import org.fix.sefixvue.management.entity.Application;
import org.fix.sefixvue.management.model.dto.ApplicationDto;
import org.fix.sefixvue.management.model.service.ManagementService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class ManagementController {
    private final ManagementService managementService;
    @GetMapping("/management/deptapplist")  // 담당 부서 신청 페이지 리스트 작성용
    public Header<List<ApplicationDto>> applicationList(
            @PageableDefault(sort = {"appno"}) Pageable pageable,
            SearchCondition searchCondition
    ) {
        return managementService.getApplicationList(pageable, searchCondition);
    }

    @GetMapping("/management/emplist{empno}")   // 담당 부서 이동 페이지 개인정보 상세보기용
    public EmployeeDto getEmpApp(@PathVariable Long empno) {
        return managementService.getEmpApp(empno);
    }

    @GetMapping("/management/applist{appno}")   // 담당 부서 신청 페이지 신청내용 상세보기용
    public ApplicationDto getApp(@PathVariable Long appno) {
        return managementService.getApp(appno);
    }

    @PostMapping("/management/yesmove")    //  승인 등록용
    public Application appWrite(@RequestBody ApplicationDto applicationDto) {
        return managementService.create(applicationDto);
    }

    @PatchMapping("/management/yesmove")   // 승인 수정용
    public Application appUp(@RequestBody ApplicationDto applicationDto){
        return managementService.update(applicationDto);
    }

    @PostMapping("/management/considerationmove")    //  검토중 등록용
    public Application appWriteC(@RequestBody ApplicationDto applicationDto) {
        return managementService.createC(applicationDto);
    }

    @PatchMapping("/management/considerationmove")   // 검토중 수정용
    public Application appUpC(@RequestBody ApplicationDto applicationDto){
        return managementService.updateC(applicationDto);
    }

    @PostMapping("/management/nomove")    //  반려 등록용
    public Application appWriteN(@RequestBody ApplicationDto applicationDto) {
        return managementService.createN(applicationDto);
    }

    @PatchMapping("/management/nomove")   // 반려 수정용
    public Application appUpN(@RequestBody ApplicationDto applicationDto){
        return managementService.updateN(applicationDto);
    }

    @PatchMapping("/management/hrmup")   // 작성 페이지 부서 변경용
    public Employee memberUp(@RequestBody EmployeeDto employeeDto){
        return managementService.memberUp(employeeDto);
    }///management/hrmup

}
