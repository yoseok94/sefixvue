package org.fix.sefixvue.accounting.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.accounting.entity.*;
import org.fix.sefixvue.accounting.model.dto.AllowancesDeductionsDto;
import org.fix.sefixvue.accounting.model.dto.SalaryDto;
import org.fix.sefixvue.accounting.model.dto.SlipstatementDto;
import org.fix.sefixvue.accounting.model.dto.TradeSummaryDto;
import org.fix.sefixvue.business.entity.Trade;
import org.fix.sefixvue.business.entity.TradeRepository;
import org.fix.sefixvue.business.entity.TradeRepositoryCustom;
import org.fix.sefixvue.business.model.dto.TradeDto;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.Pagination;
import org.fix.sefixvue.common.SearchCondition;
import org.fix.sefixvue.hrm.entity.Employee;
import org.fix.sefixvue.hrm.entity.EmployeeRepository;
import org.fix.sefixvue.hrm.model.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static org.aspectj.runtime.internal.Conversions.intValue;
import static org.fix.sefixvue.accounting.entity.QSalary.salary;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class AccountingService {

    private final SalaryRepository salaryRepository;
    private final SalaryRepositoryCustom salaryRepositoryCustom;
    private final AllowancesDeductionsRepository allowancesDeductionsRepository;
    private final AllowancesDeductionsRepositoryCustom allowancesDeductionsRepositoryCustom;
    private final SlipstatementRepository slipstatementRepository;
    private final SlipstatementRepositoryCustom slipstatementRepositoryCustom;
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final TradeRepository tradeRepository;
    private final TradeRepositoryCustom tradeRepositoryCustom;

    private DecimalFormat dformatter = new DecimalFormat("###,###");
    private SalaryDto salaryDto;

    public Header<List<SalaryDto>> getSalary(Pageable pageable, SearchCondition searchCondition) {
        List<SalaryDto> salaryDtos = new ArrayList<>();
        Page<Salary> salarys = salaryRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        for (Salary salary : salarys) {
            SalaryDto salaryDto = SalaryDto.builder()
                    .salaryno(salary.getSalaryno())
                    .paymentdate(salary.getPaymentdate())
                    .empId(salary.getEmpId())
                    .empname(salary.getEmpname())
                    .deptname(salary.getDeptname())
                    .emplevel(salary.getEmplevel())
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

    public SalaryDto getSalaryDetailList(String empId, java.util.Date paymentdate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(paymentdate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        java.util.Date startOfDay = cal.getTime();

        cal.add(Calendar.DAY_OF_MONTH, 1);
        java.util.Date nextDay = cal.getTime();

        Salary entity = salaryRepository.findSalaryByTruncPaymentdateAndEmpId(startOfDay, nextDay, empId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        return SalaryDto.builder()
                .salaryno(entity.getSalaryno())
                .paymentdate(entity.getPaymentdate())
                .emphiredate(entity.getEmphiredate())
                .empId(entity.getEmpId())
                .empname(entity.getEmpname())
                .deptname(entity.getDeptname())
                .emplevel(entity.getEmplevel())
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
    public BigDecimal calculateHealthInsurancePremium(long totalPaymentSalary) {
        BigDecimal salary = BigDecimal.valueOf(totalPaymentSalary).subtract(new BigDecimal("200000"));
        BigDecimal healthInsurancePremium = salary.multiply(new BigDecimal("0.03545"));
        return healthInsurancePremium.setScale(0, RoundingMode.HALF_UP);
    }

    // 고용보험료
    public BigDecimal calculateEmploymentInsurancePremium(long totalPaymentSalary) {
        BigDecimal salary = BigDecimal.valueOf(totalPaymentSalary).subtract(new BigDecimal("200000"));
        BigDecimal employmentInsurancePremium = salary.multiply(new BigDecimal("0.009"));
        return employmentInsurancePremium.setScale(0, RoundingMode.HALF_UP);
    }

    // 국민연금료
    public BigDecimal calculateNationalPensionFee(long totalPaymentSalary) {
        BigDecimal salary = BigDecimal.valueOf(totalPaymentSalary);
        BigDecimal nationalPensionFee;
        if (salary.compareTo(new BigDecimal("5530000")) <= 0) {
            nationalPensionFee = salary.multiply(new BigDecimal("0.045"));
        } else {
            nationalPensionFee = new BigDecimal("5530000").multiply(new BigDecimal("0.045"));
        }
        return nationalPensionFee.setScale(0, RoundingMode.HALF_UP);
    }

    // 근로소득세
    public BigDecimal earnedIncomeTax(long totalPaymentSalary) {
        BigDecimal salary = BigDecimal.valueOf(totalPaymentSalary * 12);
        BigDecimal earnedIncomeTax;
        if (salary.compareTo(new BigDecimal("14000000")) < 0) {
            earnedIncomeTax = salary.multiply(new BigDecimal("0.06"));
        } else if (salary.compareTo(new BigDecimal("50000000")) < 0) {
            earnedIncomeTax = new BigDecimal("840000").add((salary.subtract(new BigDecimal("14000000"))).multiply(new BigDecimal("0.15")));
        } else if (salary.compareTo(new BigDecimal("88000000")) < 0) {
            earnedIncomeTax = new BigDecimal("6240000").add((salary.subtract(new BigDecimal("50000000"))).multiply(new BigDecimal("0.24")));
        } else if (salary.compareTo(new BigDecimal("150000000")) < 0) {
            earnedIncomeTax = new BigDecimal("15360000").add((salary.subtract(new BigDecimal("88000000"))).multiply(new BigDecimal("0.35")));
        } else if (salary.compareTo(new BigDecimal("300000000")) < 0) {
            earnedIncomeTax = new BigDecimal("37060000").add((salary.subtract(new BigDecimal("150000000"))).multiply(new BigDecimal("0.38")));
        } else if (salary.compareTo(new BigDecimal("500000000")) < 0) {
            earnedIncomeTax = new BigDecimal("94060000").add((salary.subtract(new BigDecimal("300000000"))).multiply(new BigDecimal("0.40")));
        } else if (salary.compareTo(new BigDecimal("1000000000")) < 0) {
            earnedIncomeTax = new BigDecimal("174060000").add((salary.subtract(new BigDecimal("500000000"))).multiply(new BigDecimal("0.42")));
        } else {
            earnedIncomeTax = new BigDecimal("384060000").add((salary.subtract(new BigDecimal("1000000000"))).multiply(new BigDecimal("0.45")));
        }
        return earnedIncomeTax.divide(new BigDecimal("12"), 0, RoundingMode.HALF_UP);
    }
    // 장기요양보험료
    public BigDecimal calculateLongTermCareInsurancePremium(long totalPaymentSalary) {
        BigDecimal salary = BigDecimal.valueOf(totalPaymentSalary);
        BigDecimal healthInsurancePremium = salary.multiply(new BigDecimal("0.03545"));
        BigDecimal longTermCareInsurancePremium = healthInsurancePremium.multiply(new BigDecimal("0.1281"));
        return longTermCareInsurancePremium.setScale(0, RoundingMode.HALF_UP);
    }



    // 지방소득세
    public BigDecimal localIncomeTax(long totalPaymentSalary) {
        BigDecimal earnedIncomeTax = earnedIncomeTax(totalPaymentSalary);
        BigDecimal localIncomeTax = earnedIncomeTax.multiply(new BigDecimal("0.1"));
        return localIncomeTax.setScale(0, RoundingMode.HALF_UP);
    }
    // 총 공제액 계산기
    public BigDecimal totalDeductions(long  totalPaymentSalary) {
        BigDecimal healthInsurancePremium = calculateHealthInsurancePremium(totalPaymentSalary);
        BigDecimal employmentInsurancePremium = calculateEmploymentInsurancePremium(totalPaymentSalary);
        BigDecimal nationalPensionFee = calculateNationalPensionFee(totalPaymentSalary);
        BigDecimal longTermCareInsurancePremium = calculateLongTermCareInsurancePremium(totalPaymentSalary);
        BigDecimal earnedIncomeTax = earnedIncomeTax(totalPaymentSalary);
        BigDecimal localIncomeTax = localIncomeTax(totalPaymentSalary);

        BigDecimal totalDeductions = healthInsurancePremium
                .add(employmentInsurancePremium)
                .add(nationalPensionFee)
                .add(longTermCareInsurancePremium)
                .add(earnedIncomeTax)
                .add(localIncomeTax);
        return totalDeductions.setScale(0, RoundingMode.HALF_UP);
    }
    // 실제지급액 계산기
    public BigDecimal calculateActualPayment(long totalPaymentSalary) {
        BigDecimal salary = BigDecimal.valueOf(totalPaymentSalary);
        BigDecimal totalDeductions = totalDeductions(totalPaymentSalary);
        BigDecimal actualpaymentsalary = salary.subtract(totalDeductions);
        return actualpaymentsalary.setScale(0, RoundingMode.HALF_UP);
    }


    public Salary salaryWrite(Salary salary) {
        // 계산 로직 수행
        long totalPayment = salary.getTotalpaymentsalary();

        salary.setEmpId(salary.getEmpId());
        salary.setBasesalary(salary.getBasesalary());
        salary.setOvertimesalary(salary.getOvertimesalary());
        salary.setTotalpaymentsalary(salary.getTotalpaymentsalary());
        salary.setEarnedincometax(earnedIncomeTax(totalPayment).intValue());
        salary.setLocalincometax(localIncomeTax(totalPayment).intValue());
        salary.setNationalpensionfee(calculateNationalPensionFee(totalPayment).intValue());
        salary.setHealthinsurancepremium(calculateHealthInsurancePremium(totalPayment).intValue());
        salary.setEmploymentinsurancepremium(calculateEmploymentInsurancePremium(totalPayment).intValue());
        salary.setLongtermcareinsurancepremium(calculateLongTermCareInsurancePremium(totalPayment).intValue());
        salary.setTotaldeductionsamount(totalDeductions(totalPayment).intValue());
        salary.setActualpaymentsalary(calculateActualPayment(totalPayment).intValue());

        // 수정된 객체를 저장
        return salaryRepository.save(salary);
    }

    public EmployeeDto getEmployee(String empId) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmpId(empId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
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
                    .empprofile(employee.getEmpprofile())
                    .build();
        } else {
            throw new RuntimeException("Could not find employee with ID: " + empId);
        }
    }

    public AllowancesDeductions adWrite(AllowancesDeductions allowancesDeductions) {
        return allowancesDeductionsRepository.save(allowancesDeductions);
    }
    public void adRemove(String adcode) {
        allowancesDeductionsRepository.deleteByAdcode(adcode);
    }
    public Slipstatement slipWrite(Slipstatement slipstatement) {
        return slipstatementRepository.save(slipstatement);
    }

    public List<TradeSummaryDto> monthlySales() {
        List<Object[]> results = tradeRepository.findMonthlySales();
        List<TradeSummaryDto> tradeSummaryDtoList = results.stream()
                .map(result -> new TradeSummaryDto(((Number) result[0]).intValue(),
                        ((Number) result[1]).intValue(),
                        ((Number) result[2]).longValue()))
                .collect(Collectors.toList());
        System.out.println(tradeSummaryDtoList);
        return tradeSummaryDtoList;
    }

    public Header<List<TradeDto>> getRevenuelist(Pageable pageable, SearchCondition searchCondition) {
            List<TradeDto> tradeDtos = new ArrayList<>();
            Page<Trade> trades = tradeRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
            for (Trade trade : trades) {
                TradeDto tradeDto = TradeDto.builder()
                        .tradingno(trade.getTradingno())
                        .tradingday(trade.getTradingday())
                        .duedate(trade.getDuedate())
                        .productname(trade.getProductname())
                        .unitprice(trade.getUnitprice())
                        .quantity(trade.getQuantity())
                        .tax(trade.getTax())
                        .totalprice(trade.getTotalprice())
                        .ordersdate(trade.getOrdersdate())
                        .orderstype(trade.getOrderstype())
                        .ordersprice(trade.getOrdersprice())
                        .totalordersprice(trade.getTotalordersprice())
                        .accountname(trade.getAccountname())
                        .tradetype(trade.getTradetype())
                        .build();
                tradeDtos.add(tradeDto);
            }
            Pagination pagination = new Pagination(
                    (int) trades.getTotalElements()
                    , pageable.getPageNumber() + 1
                    , pageable.getPageSize()
                    , 10
            );
            return Header.OK(tradeDtos, pagination);
        }
    }



