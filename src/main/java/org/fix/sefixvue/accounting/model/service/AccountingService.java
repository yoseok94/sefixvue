package org.fix.sefixvue.accounting.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.accounting.entity.*;
import org.fix.sefixvue.accounting.model.dto.AllowancesDeductionsDto;
import org.fix.sefixvue.accounting.model.dto.SalaryDto;
import org.fix.sefixvue.accounting.model.dto.SlipstatementDto;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.Pagination;
import org.fix.sefixvue.common.SearchCondition;
import org.fix.sefixvue.hrm.entity.Employee;
import org.fix.sefixvue.hrm.entity.EmployeeRepository;
import org.fix.sefixvue.hrm.model.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class AccountingService {

    private final SalaryRepository salaryRepository;
    private final SalaryRepositoryCustom salaryRepositoryCustom;
    private final AllowancesDeductionsRepository allowancesDeductionRepository;
    private final AllowancesDeductionsRepositoryCustom allowancesDeductionsRepositoryCustom;
    private final SlipstatementRepository slipstatementRepository;
    private final SlipstatementRepositoryCustom slipstatementRepositoryCustom;
    private final EmployeeRepository employeeRepository;

//    값 입력하는 WRITE 해줄 때 실행해주기
//                        .basesalary(dformatter.format(salary.getBasesalary()))
//            .overtimesalary(dformatter.format(salary.getOvertimesalary()))
//            .totalpaymentsalary(dformatter.format(salary.getTotalpaymentsalary()))
//            .earnedincometax(dformatter.format(earnedIncomeTax(BigDecimal.valueOf(salary.getTotalpaymentsalary()))))
//            .localincomtax(dformatter.format(localIncomeTax(BigDecimal.valueOf(salary.getTotalpaymentsalary()))))
//            .nationalpensionfee(dformatter.format(calculateNationalPensionFee(BigDecimal.valueOf(salary.getTotalpaymentsalary()))))
//            .healthinsurancepremium(dformatter.format(calculateHealthInsurancePremium(BigDecimal.valueOf(salary.getTotalpaymentsalary()))))
//            .employmentinsurancepremium(dformatter.format(calculateEmploymentInsurancePremium(BigDecimal.valueOf(salary.getTotalpaymentsalary()))))
//            .longtermcareinsurancepremium(dformatter.format(calculateLongTermCareInsurancePremium(BigDecimal.valueOf(salary.getTotalpaymentsalary()))))
//            .totaldeductionsamount(dformatter.format(totalDeductions(BigDecimal.valueOf(salary.getTotalpaymentsalary()))))
//            .actualpaymentsalary(dformatter.format(longValue(calculateActualPayment(BigDecimal.valueOf(salary.getTotalpaymentsalary())))))
//
    private DecimalFormat dformatter = new DecimalFormat("###,###");
//    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public Header<List<SalaryDto>> getSalary(Pageable pageable, SearchCondition searchCondition) {
        List<SalaryDto> salaryDtos = new ArrayList<>();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        Page<Salary> salarys = salaryRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);

        for (Salary salary : salarys) {

            SalaryDto salaryDto = SalaryDto.builder()
                    .salaryno(salary.getSalaryno())
                    .paymentdate(salary.getPaymentdate())
                    .empid(salary.getEmpid())
                    .empname(salary.getEmpname())
                    .deptname(salary.getDeptname())
                    .jobname(salary.getEmplevel())
                    .workhours(salary.getWorkhours())
                    .overtimehours(salary.getOvertimehours())
                    .basesalary(dformatter.format(salary.getBasesalary()))
                    .overtimesalary(dformatter.format(salary.getOvertimesalary()))
                    .totalpaymentsalary(dformatter.format(salary.getTotalpaymentsalary()))
                    .earnedincometax(dformatter.format(salary.getEarnedincometax()))
                    .localincomtax(dformatter.format(salary.getLocalincometax()))
                    .nationalpensionfee(dformatter.format(salary.getNationalpensionfee()))
                    .healthinsurancepremium(dformatter.format(salary.getHealthinsurancepremium()))
                    .employmentinsurancepremium(dformatter.format(salary.getEmploymentinsurancepremium()))
                    .longtermcareinsurancepremium(dformatter.format(salary.getLongtermcareinsurancepremium()))
                    .totaldeductionsamount(dformatter.format(salary.getTotaldeductionsamount()))
                    .actualpaymentsalary(dformatter.format(salary.getActualpaymentsalary()))
                    .build();
            salaryDtos.add(salaryDto);
        }

        Pagination pagination = new Pagination(
                (int) salarys.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );
        return Header.OK(salaryDtos, pagination);
    }

    public SalaryDto getSalaryDetailList(String empid, java.util.Date paymentdate) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(paymentdate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        java.util.Date startOfDay = cal.getTime();

        cal.add(Calendar.DAY_OF_MONTH, 1);
        java.util.Date nextDay = cal.getTime();

        Salary entity = salaryRepository.findSalaryByTruncPaymentdateAndEmpid(startOfDay, nextDay, empid)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        return SalaryDto.builder()
                .salaryno(entity.getSalaryno())
                .paymentdate(entity.getPaymentdate())
                .empid(entity.getEmpid())
                .empname(entity.getEmpname())
                .deptname(entity.getDeptname())
                .jobname(entity.getEmplevel())
                .workhours(entity.getWorkhours())
                .overtimehours(entity.getOvertimehours())
                .basesalary(dformatter.format(entity.getBasesalary()))
                .overtimesalary(dformatter.format(entity.getOvertimesalary()))
                .totalpaymentsalary(dformatter.format(entity.getTotalpaymentsalary()))
                .earnedincometax(dformatter.format(entity.getEarnedincometax()))
                .localincomtax(dformatter.format(entity.getLocalincometax()))
                .nationalpensionfee(dformatter.format(entity.getNationalpensionfee()))
                .healthinsurancepremium(dformatter.format(entity.getHealthinsurancepremium()))
                .employmentinsurancepremium(dformatter.format(entity.getEmploymentinsurancepremium()))
                .longtermcareinsurancepremium(dformatter.format(entity.getLongtermcareinsurancepremium()))
                .totaldeductionsamount(dformatter.format(entity.getTotaldeductionsamount()))
                .actualpaymentsalary(dformatter.format(entity.getActualpaymentsalary()))
                .build();
    }
    public Header<List<AllowancesDeductionsDto>> getAllowancesDeductionsList(Pageable pageable, SearchCondition searchCondition) {
        List<AllowancesDeductionsDto> allowancesDeductionsDtos = new ArrayList<>();
        Page<AllowancesDeductions> allowancesDeductions = allowancesDeductionsRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        for (AllowancesDeductions allowancesDeduction : allowancesDeductions) {
            AllowancesDeductionsDto allowancesDeductionsDto = AllowancesDeductionsDto.builder()
                    .adno(allowancesDeduction.getAdno())
                    .adcode(allowancesDeduction.getAdcode())
                    .adname(allowancesDeduction.getAdname())
                    .adcalculation(allowancesDeduction.getAdcalculation())
                    .adcalculationmethod(allowancesDeduction.getAdcalculationmethod())
                    .build();
            allowancesDeductionsDtos.add(allowancesDeductionsDto);
        }
        Pagination pagination = new Pagination(
                (int) allowancesDeductions.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );
        return Header.OK(allowancesDeductionsDtos, pagination);
    }


    public Header<List<SlipstatementDto>> getSlipstatement(Pageable pageable, SearchCondition searchCondition) {
        List<SlipstatementDto> slipstatementDtos = new ArrayList<>();
        Page<Slipstatement> slipstatements = slipstatementRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);

        for(Slipstatement slipstatement : slipstatements) {

            SlipstatementDto slipstatementDto = SlipstatementDto.builder()
                    .slipstatementno(slipstatement.getSlipstatementno())
                    .tradingno(slipstatement.getTrade().getTradingno())
                    .tradetype(slipstatement.getTradetype())
                    .slipstatementamount(slipstatement.getSlipstatementamount())
                    .slipstatementbrief(slipstatement.getSlipstatementbrief())
                    .slipstatementdate(slipstatement.getSlipstatementdate())
                    .build();

            slipstatementDtos.add(slipstatementDto);
        }
        Pagination pagination = new Pagination(
                (int) slipstatements.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );
        return Header.OK(slipstatementDtos, pagination);
    }



    // 건강보험료
    public BigDecimal calculateHealthInsurancePremium(BigDecimal totalpaymentsalary) {
        BigDecimal salary = totalpaymentsalary.subtract(new BigDecimal("200000"));
        BigDecimal healthinsurancepremium = salary.multiply(new BigDecimal("0.03545"));
        healthinsurancepremium = healthinsurancepremium.setScale(0, RoundingMode.HALF_UP);
        return healthinsurancepremium;
    }

    // 고용보험료
    public BigDecimal calculateEmploymentInsurancePremium(BigDecimal totalpaymentsalary) {
        BigDecimal salary = totalpaymentsalary.subtract(new BigDecimal("200000"));
        BigDecimal employmentinsurancepremium = salary.multiply(new BigDecimal("0.009"));
        employmentinsurancepremium = employmentinsurancepremium.setScale(0, RoundingMode.HALF_UP);
        return employmentinsurancepremium;
    }

    // 국민연금료
    public BigDecimal calculateNationalPensionFee(BigDecimal totalpaymentsalary) {;
        BigDecimal nationalpensionfee;
        if (totalpaymentsalary.compareTo(new BigDecimal("5530000")) <= 0) {
            nationalpensionfee = totalpaymentsalary.multiply(new BigDecimal("0.045"));
        } else {
            nationalpensionfee = new BigDecimal("5530000").multiply(new BigDecimal("0.045"));
        }
        nationalpensionfee = nationalpensionfee.setScale(0, RoundingMode.HALF_UP);
        return nationalpensionfee;
    }

    // 근로소득세
    // 근로소득세는 지방소득세 계산식에 사용해야되므로 일단 BigDecimal 타입으로 리턴해준 뒤 나중에 사용할 때 longValue() 해주자.
    public BigDecimal earnedIncomeTax(BigDecimal totalpaymentsalary) {
        BigDecimal earnedincometax;
        if (totalpaymentsalary.compareTo(new BigDecimal("14000000")) < 0) {
            earnedincometax = totalpaymentsalary.multiply(new BigDecimal("0.06"));
        } else if (totalpaymentsalary.compareTo(new BigDecimal("50000000")) < 0) {
            earnedincometax = new BigDecimal("840000").add((totalpaymentsalary.subtract(new BigDecimal("14000000"))).multiply(new BigDecimal("0.15")));
        } else if (totalpaymentsalary.compareTo(new BigDecimal("88000000")) < 0) {
            earnedincometax = new BigDecimal("6240000").add((totalpaymentsalary.subtract(new BigDecimal("50000000"))).multiply(new BigDecimal("0.24")));
        } else if (totalpaymentsalary.compareTo(new BigDecimal("150000000")) < 0) {
            earnedincometax = new BigDecimal("15360000").add((totalpaymentsalary.subtract(new BigDecimal("88000000"))).multiply(new BigDecimal("0.35")));
        } else if (totalpaymentsalary.compareTo(new BigDecimal("300000000")) < 0) {
            earnedincometax = new BigDecimal("37060000").add((totalpaymentsalary.subtract(new BigDecimal("150000000"))).multiply(new BigDecimal("0.38")));
        } else if (totalpaymentsalary.compareTo(new BigDecimal("500000000")) < 0) {
            earnedincometax = new BigDecimal("94060000").add((totalpaymentsalary.subtract(new BigDecimal("300000000"))).multiply(new BigDecimal("0.40")));
        } else if (totalpaymentsalary.compareTo(new BigDecimal("1000000000")) < 0) {
            earnedincometax = new BigDecimal("174060000").add((totalpaymentsalary.subtract(new BigDecimal("500000000"))).multiply(new BigDecimal("0.42")));
        } else {
            earnedincometax = new BigDecimal("384060000").add((totalpaymentsalary.subtract(new BigDecimal("1000000000"))).multiply(new BigDecimal("0.45")));
        }
        earnedincometax = earnedincometax.setScale(0, RoundingMode.HALF_UP);
        return earnedincometax;
    }

    // 장기요양보험료
    public BigDecimal calculateLongTermCareInsurancePremium(BigDecimal totalpaymentsalary) {

        BigDecimal healthinsurancepremium = totalpaymentsalary.multiply(new BigDecimal("0.03545"));
        BigDecimal longtermcareinsurancepremium = healthinsurancepremium.multiply(new BigDecimal("0.1281"));
        longtermcareinsurancepremium = longtermcareinsurancepremium.setScale(0, RoundingMode.HALF_UP);
        return longtermcareinsurancepremium;
    }

    // 지방소득세
    public BigDecimal localIncomeTax(BigDecimal totalpaymentsalary) {
        BigDecimal earnedincometax = earnedIncomeTax(totalpaymentsalary);
        BigDecimal localincomtax = earnedincometax.multiply(new BigDecimal("0.1"));
        localincomtax = localincomtax.setScale(0, RoundingMode.HALF_UP);
        return localincomtax;
    }

    // 총 공제액 계산기
    public BigDecimal totalDeductions(BigDecimal totalpaymentsalary) {
        BigDecimal healthinsurancepremium = calculateHealthInsurancePremium(totalpaymentsalary);
        BigDecimal employmentinsurancepremium = calculateEmploymentInsurancePremium(totalpaymentsalary);
        BigDecimal nationalpensionfee = calculateNationalPensionFee(totalpaymentsalary);
        BigDecimal longtermcareinsurancepremium = calculateLongTermCareInsurancePremium(totalpaymentsalary);
        BigDecimal earnedincometax = earnedIncomeTax(totalpaymentsalary);
        BigDecimal localincomtax = localIncomeTax(earnedincometax);

        BigDecimal totaldeductions = healthinsurancepremium
                .add(employmentinsurancepremium)
                .add(nationalpensionfee)
                .add(longtermcareinsurancepremium)
                .add(earnedincometax)
                .add(localincomtax);
        totaldeductions = totaldeductions.setScale(0, RoundingMode.HALF_UP);
        return totaldeductions;
    }
    // 실제지급액 계산기
    public BigDecimal calculateActualPayment(BigDecimal totalpaymentsalary) {

        BigDecimal totalDeductions = totalDeductions(totalpaymentsalary);
        BigDecimal actualpaymentsalary = totalpaymentsalary.subtract(totalDeductions);
        actualpaymentsalary = actualpaymentsalary.setScale(0, RoundingMode.HALF_UP);
        return actualpaymentsalary;
    }


    public EmployeeDto getEmployee(String empid) {
        Employee employee = employeeRepository.findByEmpId(empid).orElseThrow(() -> new RuntimeException("사원 정보를 찾을 수 없습니다."));
        return EmployeeDto.builder()
                .empno(employee.getEmpno())
                .empId(employee.getEmpId())
                .emppw(employee.getEmppw())
                .empname(employee.getEmpname())
                .empphone(employee.getEmpphone())
                .empaddress(employee.getEmpaddress())
                .empemail(employee.getEmpemail())
                .empbirth(employee.getEmpbirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .emphiredate(employee.getEmphiredate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .emplevel(employee.getEmplevel())
                .empstatus(employee.getEmpstatus())
                .deptname(employee.getDeptname())
                .empannual(employee.getEmpannual())
                .empprofile(employee.getEmpprofile())
                .build();
    }
    public Salary salaryWrite(Salary salary) {
        return salaryRepository.save(salary);
    }

    public AllowancesDeductions adWrite(AllowancesDeductions allowancesDeductions) {
        return null;
    }


    public Slipstatement slipWrite(Slipstatement slipstatement) {
        return null;
    }

    public Salary salaryModify(Salary salary) {
        return null;
    }

    public AllowancesDeductions adModify(AllowancesDeductions allowancesDeductions) {
        return null;
    }

    public Slipstatement slipModify(Slipstatement slipstatement) {
        return null;
    }
}
