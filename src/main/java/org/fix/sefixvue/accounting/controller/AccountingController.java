package org.fix.sefixvue.accounting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.accounting.entity.*;
import org.fix.sefixvue.accounting.model.dto.AllowancesDeductionsDto;
import org.fix.sefixvue.accounting.model.dto.SalaryDto;
import org.fix.sefixvue.accounting.model.dto.SlipstatementDto;
import org.fix.sefixvue.accounting.model.service.AccountingService;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.SearchCondition;
import org.fix.sefixvue.hrm.entity.Employee;
import org.fix.sefixvue.hrm.model.dto.EmployeeDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@RequestMapping("/accounting")
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class AccountingController {

    private final AccountingService accountingService;


    @GetMapping("/salarylist")
    public Header<List<SalaryDto>> salaryList
            (@PageableDefault(sort={"salaryno"}) Pageable pageable, SearchCondition searchCondition) {
        return accountingService.getSalary(pageable, searchCondition);
    }
    @GetMapping("/payslib/{empid}/{paymentdate}")
    public SalaryDto getSalaryDetailList(@PathVariable String empid, @PathVariable String paymentdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date paymentdates = null;
        try {
            paymentdates = sdf.parse(paymentdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(empid);
        System.out.println(paymentdates);
        return accountingService.getSalaryDetailList(empid, paymentdates);
    }
    @GetMapping("/adlist")
    public Header<List<AllowancesDeductionsDto>> adList(
            @PageableDefault(sort = {"adno"}) Pageable pageable, SearchCondition searchCondition) {
        return accountingService.getAllowancesDeductionsList(pageable, searchCondition);
    }

    @GetMapping("/sliplist")
    public Header<List<SlipstatementDto>> slipList
            (@PageableDefault(sort={"slipstatementno"}) Pageable pageable, SearchCondition searchCondition) {
        return accountingService.getSlipstatement(pageable, searchCondition);
    }

    @GetMapping("/employee{empid}")
    public EmployeeDto getEmployee(@PathVariable String empid) {
        return accountingService.getEmployee(empid);
    }

    @PostMapping("/salarywrite")
    public Salary salaryWrite(@RequestBody Salary salary){
        return accountingService.salaryWrite(salary);
    }

    @PostMapping("/adwrite")
    public AllowancesDeductions adWrite(@RequestBody AllowancesDeductions allowancesDeductions) {
        return accountingService.adWrite(allowancesDeductions);
    }

    @PostMapping("/slipwrite")
    public Slipstatement slipWrite(@RequestBody Slipstatement slipstatement) {
        return accountingService.slipWrite(slipstatement);
    }

    @PatchMapping("/salarymodify")
    public Salary salaryModify(@RequestBody Salary salary) {
        return accountingService.salaryModify(salary);
    }

    @PatchMapping("/admodify")
    public AllowancesDeductions adModify(@RequestBody AllowancesDeductions allowancesDeductions) {
        return accountingService.adModify(allowancesDeductions);
    }

    @PatchMapping("/slipmodify")
    public Slipstatement slipModify(@RequestBody Slipstatement slipstatement) {
        return accountingService.slipModify(slipstatement);
    }
}
