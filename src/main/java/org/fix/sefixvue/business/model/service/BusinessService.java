package org.fix.sefixvue.business.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.admin.entity.Product;
import org.fix.sefixvue.admin.entity.ProductRepository;
import org.fix.sefixvue.admin.model.dto.ProductDto;
import org.fix.sefixvue.business.entity.*;
import org.fix.sefixvue.business.model.dto.AccountDto;
import org.fix.sefixvue.business.model.dto.PlanDisbursementDto;
import org.fix.sefixvue.hrm.entity.Employee;
import org.fix.sefixvue.hrm.entity.EmployeeRepository;
import org.fix.sefixvue.hrm.model.dto.EmployeeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.fix.sefixvue.business.model.dto.PlanDisbursementDto;
import org.fix.sefixvue.business.entity.AccountRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class BusinessService {
    private final AccountRepository accountRepository;
    private final PlanDisbursementRepository planDisbursementRepository;
    private final TradeRepository tradeRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;


    // 영업계획 목록 가져오기
    public List<PlanDisbursementDto> getPlanList() {
        List<PlanDisbursement> planDisbursements = planDisbursementRepository.findAll();
        List<PlanDisbursementDto> planDisbursementDtos = new ArrayList<>();

        for (PlanDisbursement planDisbursement : planDisbursements) {
            Product product = getProductById(planDisbursement.getProductno());
            String productName = product != null ? product.getProductname() : "";

            Employee employee = getEmployeeByEmpId(planDisbursement.getEmpId());
            String employeeName = employee != null ? employee.getEmpname() : "";
            String employeeLevel = employee != null ? employee.getEmplevel() : "";

            PlanDisbursementDto dto = PlanDisbursementDto.builder()
                    .planno(planDisbursement.getPlanno())
                    .plantitle(planDisbursement.getPlantitle())
                    .targetquantity(planDisbursement.getTargetquantity())
                    .targetamount(planDisbursement.getTargetamount())
                    .plandetail(planDisbursement.getPlandetail())
                    .planremarks(planDisbursement.getPlanremarks())
                    .plandate(planDisbursement.getPlandate())
                    .deptname(planDisbursement.getDeptname())
                    .empId(planDisbursement.getEmpId())
                    .accountname(planDisbursement.getAccountname())
                    .productno(planDisbursement.getProductno())
                    .productname(productName)
                    .empname(employeeName)
                    .emplevel(employeeLevel)
                    .build();
            planDisbursementDtos.add(dto);
        }
        return planDisbursementDtos;
    }

    // 영업계획 등록
    public void addPlan(PlanDisbursementDto planDto) {

        if (planDto.getDisbursementdetail() == null) {
            planDto.setDisbursementdetail("");
        }

        PlanDisbursement plan = PlanDisbursement.builder()
                .plantitle(planDto.getPlantitle())
                .targetquantity(planDto.getTargetquantity())
                .targetamount(planDto.getTargetamount())
                .plandetail(planDto.getPlandetail())
                .planremarks(planDto.getPlanremarks())
                .plandate(new java.sql.Date(System.currentTimeMillis()))
                .disbursementdetail(planDto.getDisbursementdetail())
                .empId(planDto.getEmpId())
                .accountname(planDto.getAccountname())
                .deptname(planDto.getDeptname())
                .productno(planDto.getProductno())

                .quantity(planDto.getQuantity())
                .disbursementsupplyprice(planDto.getDisbursementsupplyprice())
                .totalamount(planDto.getTotalamount())
                .tradetype(planDto.getTradetype())
                .disbursementdetail(planDto.getDisbursementdetail())
                .status(planDto.getStatus())
                .build();

        planDisbursementRepository.save(plan);
    }
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public String getProductByName(Long productno) {
        Optional<Product> productOptional = productRepository.findById(productno);
        return productOptional.map(Product::getProductname).orElse("");
    }

    //상품 구매가 조회
    public int getProductPurchasePrice(Long productno) {
        Product product = productRepository.findById(productno).orElse(null);
        if (product != null) {
            return product.getPurchaseprice();
        }
        return 0;
    }

    // 상품 조회
    private Product getProductById(Long productno) {

        return productRepository.findById(productno).orElse(null);
    }



    // 영업계획 수정
    public void editPlan(Long planno, PlanDisbursementDto planDto) {
        Optional<PlanDisbursement> planOptional = planDisbursementRepository.findById(planno);
        if (planOptional.isPresent()) {
            PlanDisbursement plan = planOptional.get();
            plan.setPlantitle(planDto.getPlantitle());
            plan.setTargetquantity(planDto.getTargetquantity());
            plan.setTargetamount(planDto.getTargetamount());
            plan.setPlandetail(planDto.getPlandetail());
            plan.setPlanremarks(planDto.getPlanremarks());
            plan.setPlandate(new java.sql.Date(System.currentTimeMillis()));
            plan.setDeptname(planDto.getDeptname());
            plan.setEmpId(planDto.getEmpId());
            plan.setProductno(planDto.getProductno());

            planDisbursementRepository.save(plan);
        } else {
            log.error("Plan with planno {} not found", planno);
            throw new IllegalArgumentException("영업계획을 찾을 수 없습니다.");
        }
    }

    // 영업계획 삭제
    public void deletePlans(List<Long> planno) {
        List<PlanDisbursement> plans = planDisbursementRepository.findAllById(planno);
        planDisbursementRepository.deleteAll(plans);
    }


    // 영업계획 상세 조회
    public PlanDisbursementDto getPlanById(Long planno) {
        Optional<PlanDisbursement> planOptional = planDisbursementRepository.findById(planno);
        if (planOptional.isPresent()) {
            PlanDisbursement plan = planOptional.get();
            Product product = getProductById(plan.getProductno());
            String productName = product != null ? product.getProductname() : "";

            Employee employee = getEmployeeByEmpId(plan.getEmpId());
            String employeeName = employee != null ? employee.getEmpname() : "";
            String employeeLevel = employee != null ? employee.getEmplevel() : "";

            PlanDisbursementDto dto = PlanDisbursementDto.builder()
                    .planno(plan.getPlanno())
                    .plantitle(plan.getPlantitle())
                    .targetquantity(plan.getTargetquantity())
                    .targetamount(plan.getTargetamount())
                    .plandetail(plan.getPlandetail())
                    .planremarks(plan.getPlanremarks())
                    .plandate(plan.getPlandate())
                    .deptname(plan.getDeptname())
                    .empId(plan.getEmpId())
                    .accountname(plan.getAccountname())
                    .productno(plan.getProductno())
                    .productname(productName)
                    .empname(employeeName)
                    .emplevel(employeeLevel)
                    .build();
            return dto;
        } else {
            log.error("Plan with planno {} not found", planno);
            throw new IllegalArgumentException("영업계획을 찾을 수 없습니다.");
        }
    }


    // 영업지출 결의서 목록 가져오기
    public List<PlanDisbursementDto> getDisbursementList() {
        List<PlanDisbursement> planDisbursements = planDisbursementRepository.findAll();
        List<PlanDisbursementDto> planDisbursementDtos = new ArrayList<>();

        for (PlanDisbursement planDisbursement : planDisbursements) {
            Product product = getProductById(planDisbursement.getProductno());
            String productName = product != null ? product.getProductname() : "";

            Employee employee = getEmployeeByEmpId(planDisbursement.getEmpId());
            String employeeName = employee != null ? employee.getEmpname() : "";
            String employeeLevel = employee != null ? employee.getEmplevel() : "";

            PlanDisbursementDto dto = PlanDisbursementDto.builder()
                    .planno(planDisbursement.getPlanno())
                    .plantitle(planDisbursement.getPlantitle())
                    .targetquantity(planDisbursement.getTargetquantity())
                    .targetamount(planDisbursement.getTargetamount())
                    .plandetail(planDisbursement.getPlandetail())
                    .planremarks(planDisbursement.getPlanremarks())
                    .plandate(planDisbursement.getPlandate())
                    .quantity(planDisbursement.getQuantity())
                    .disbursementsupplyprice(planDisbursement.getDisbursementsupplyprice())
                    .disbursementtax(planDisbursement.getDisbursementtax())
                    .totalamount(planDisbursement.getTotalamount())
                    .tradetype(planDisbursement.getTradetype())
                    .disbursementdetail(planDisbursement.getDisbursementdetail())
                    .disbursementremarks(planDisbursement.getDisbursementremarks())
                    .sentdate(planDisbursement.getSentdate())
                    .status(planDisbursement.getStatus())
                    .deptname(planDisbursement.getDeptname())
                    .empId(planDisbursement.getEmpId())
                    .accountname(planDisbursement.getAccountname())
                    .productno(planDisbursement.getProductno())
                    .productname(productName)
                    .empname(employeeName)
                    .emplevel(employeeLevel)
                    .build();
            planDisbursementDtos.add(dto);
        }
        return planDisbursementDtos;
    }

    // 영업지출 등록/수정
    public void editDisbursement(Long planno, PlanDisbursementDto disbursementDto) {
        Optional<PlanDisbursement> disbursementOptional = planDisbursementRepository.findById(planno);
        if (disbursementOptional.isPresent()) {
            PlanDisbursement disbursement = disbursementOptional.get();
            disbursement.setPlantitle(disbursementDto.getPlantitle());
            disbursement.setTargetquantity(disbursementDto.getTargetquantity());
            disbursement.setTargetamount(disbursementDto.getTargetamount());
            disbursement.setPlandetail(disbursementDto.getPlandetail());
            disbursement.setPlanremarks(disbursementDto.getPlanremarks());
            disbursement.setPlandate(disbursementDto.getPlandate());
            disbursement.setDeptname(disbursementDto.getDeptname());
            disbursement.setEmpId(disbursementDto.getEmpId());
            disbursement.setProductno(disbursementDto.getProductno());
            disbursement.setQuantity(disbursementDto.getQuantity());
            disbursement.setDisbursementsupplyprice(disbursementDto.getDisbursementsupplyprice());
            disbursement.setDisbursementtax(disbursementDto.getDisbursementtax());
            disbursement.setTotalamount(disbursementDto.getTotalamount());
            disbursement.setTradetype(disbursementDto.getTradetype());
            disbursement.setDisbursementdetail(disbursementDto.getDisbursementdetail());
            disbursement.setDisbursementremarks(disbursementDto.getDisbursementremarks());
            disbursement.setSentdate(new java.sql.Date(System.currentTimeMillis()));
            disbursement.setStatus(disbursementDto.getStatus());

            planDisbursementRepository.save(disbursement);
        } else {
            log.error("Disbursement with planno {} not found", planno);
            throw new IllegalArgumentException("영업지출을 찾을 수 없습니다.");
        }
    }


    // 영업지출 상세 조회
    public PlanDisbursementDto getDisbursementById(Long planno) {
        Optional<PlanDisbursement> disbursementOptional = planDisbursementRepository.findById(planno);
        if (disbursementOptional.isPresent()) {
            PlanDisbursement disbursement = disbursementOptional.get();
            Product product = getProductById(disbursement.getProductno());
            String productName = product != null ? product.getProductname() : "";

            Employee employee = getEmployeeByEmpId(disbursement.getEmpId());
            String employeeName = employee != null ? employee.getEmpname() : "";
            String employeeLevel = employee != null ? employee.getEmplevel() : "";

            PlanDisbursementDto dto = PlanDisbursementDto.builder()
                    .planno(disbursement.getPlanno())
                    .plantitle(disbursement.getPlantitle())
                    .targetquantity(disbursement.getTargetquantity())
                    .targetamount(disbursement.getTargetamount())
                    .plandetail(disbursement.getPlandetail())
                    .planremarks(disbursement.getPlanremarks())
                    .plandate(disbursement.getPlandate())
                    .quantity(disbursement.getQuantity())
                    .disbursementsupplyprice(disbursement.getDisbursementsupplyprice())
                    .disbursementtax(disbursement.getDisbursementtax())
                    .totalamount(disbursement.getTotalamount())
                    .tradetype(disbursement.getTradetype())
                    .disbursementdetail(disbursement.getDisbursementdetail())
                    .disbursementremarks(disbursement.getDisbursementremarks())
                    .sentdate(disbursement.getSentdate())
                    .status(disbursement.getStatus())
                    .deptname(disbursement.getDeptname())
                    .empId(disbursement.getEmpId())
                    .accountname(disbursement.getAccountname())
                    .productno(disbursement.getProductno())
                    .productname(productName)
                    .empname(employeeName)
                    .emplevel(employeeLevel)
                    .build();
            return dto;
        } else {
            log.error("Disbursement with planno {} not found", planno);
            throw new IllegalArgumentException("영업지출을 찾을 수 없습니다.");
        }
    }
    // 영업지출 삭제
    public void deleteDisbursement(Long planno) {
        Optional<PlanDisbursement> optionalPlanDisbursement = planDisbursementRepository.findById(planno);

        if (optionalPlanDisbursement.isPresent()) {

            PlanDisbursement planDisbursement = optionalPlanDisbursement.get();
            planDisbursement.setQuantity(0);
            planDisbursement.setDisbursementsupplyprice(0);
            planDisbursement.setDisbursementtax(0);
            planDisbursement.setTotalamount(0);
            planDisbursement.setDisbursementdetail(" ");
            planDisbursement.setDisbursementremarks(null);
            planDisbursement.setStatus(0);
            planDisbursement.setTradetype(0);
            planDisbursement.setSentdate(null);
            planDisbursementRepository.save(planDisbursement);
        } else {
            throw new IllegalArgumentException("Disbursement with planno " + planno + " not found");
        }
    }



    // 영업실적 목록 가져오기
    public List<PlanDisbursementDto> getPerformanceList() {
        List<PlanDisbursement> planDisbursements = planDisbursementRepository.findAll();
        List<PlanDisbursementDto> planDisbursementDtos = new ArrayList<>();

        for (PlanDisbursement planDisbursement : planDisbursements) {
            Product product = getProductById(planDisbursement.getProductno()); // 변경된 부분
            String productName = product != null ? product.getProductname() : "";

            Employee employee = getEmployeeByEmpId(planDisbursement.getEmpId()); // 변경된 부분
            String employeeName = employee != null ? employee.getEmpname() : "";
            String employeeLevel = employee != null ? employee.getEmplevel() : "";

            PlanDisbursementDto dto = PlanDisbursementDto.builder()
                    .planno(planDisbursement.getPlanno())
                    .targetquantity(planDisbursement.getTargetquantity())
                    .targetamount(planDisbursement.getTargetamount())
                    .quantity(planDisbursement.getQuantity())
                    .totalamount(planDisbursement.getTotalamount())
                    .sentdate(planDisbursement.getSentdate())
                    .deptname(planDisbursement.getDeptname())
                    .empId(planDisbursement.getEmpId())
                    .accountname(planDisbursement.getAccountname())
                    .productno(planDisbursement.getProductno())
                    .productname(productName)
                    .empname(employeeName)
                    .emplevel(employeeLevel)
                    .build();
            planDisbursementDtos.add(dto);
        }
        return planDisbursementDtos;
    }

    //거래처 조회
    private Account getAccountById(Long accountno){
        return accountRepository.findById(accountno).orElse(null);
    }



    // 사원 조회
    private Employee getEmployeeByEmpId(String empId) {
        return employeeRepository.findByEmpId(empId).orElse(null);
    }
    // 거래처 목록 가져오기
    public List<AccountDto> getAccountList() {
        List<Account> accountEntityList = accountRepository.findAll();
        List<AccountDto> dtos = new ArrayList<>();

        for(Account entity : accountEntityList){
            AccountDto dto = AccountDto.builder()
                    .accountno((entity.getAccountno()))
                    .accountname(entity.getAccountname())
                    .representativename(entity.getRepresentativename())
                    .accountphone(entity.getAccountphone())
                    .accountfax(entity.getAccountfax())
                    .accountaddress(entity.getAccountaddress())
                    .managername(entity.getManagername())
                    .managerphone(entity.getManagerphone())
                    .manageremail(entity.getManageremail())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }
    // 거래처 등록
    public Account insert(AccountDto accountDto){
        Account entity = Account.builder()
                .accountno(accountDto.getAccountno())
                .accountname(accountDto.getAccountname())
                .representativename(accountDto.getRepresentativename())
                .accountphone(accountDto.getAccountphone())
                .accountfax(accountDto.getAccountfax())
                .accountaddress(accountDto.getAccountaddress())
                .managername(accountDto.getManagername())
                .managerphone(accountDto.getManagerphone())
                .manageremail(accountDto.getManageremail())
                .build();
        return accountRepository.save(entity);
    }

    // 거래처 수정
    public Account update(AccountDto accountDto){
        Account entity = accountRepository.findById(accountDto.getAccountno()).orElseThrow(() -> new RuntimeException("거래처 정보를 찾을 수 없습니다."));
        entity.setAccountaddress(accountDto.getAccountaddress());
        entity.setRepresentativename(accountDto.getRepresentativename());
        entity.setAccountphone(accountDto.getAccountphone());
        entity.setAccountfax(accountDto.getAccountfax());
        entity.setAccountaddress(accountDto.getAccountaddress());
        entity.setManagername(accountDto.getManagername());
        entity.setManagerphone(accountDto.getManagerphone());
        entity.setManageremail(accountDto.getManageremail());
        return accountRepository.save(entity);
    }
    // 거래처 삭제
    public void delete(Long accountno) {
        Account entity = accountRepository.findById(accountno).orElseThrow(() -> new RuntimeException("해당 거래처를 찾을 수 없습니다."));
    }
    // 거래처명 검색 조회
    public List<AccountDto> findByAccountNameContaining(String searchName) {
        List<Account> accountEntities = accountRepository.findByAccountNameContaining(searchName);
        List<AccountDto> getAccountSearchList = new ArrayList<>();


        for(Account account : accountEntities) {
            AccountDto accountDTO = AccountDto.builder()
                    .accountname(account.getAccountname())
                    .representativename(account.getRepresentativename())
                    .accountphone(account.getManagerphone())
                    .accountfax(account.getAccountfax())
                    .accountaddress(account.getAccountaddress())
                    .managername(account.getManagername())
                    .managerphone(account.getManagerphone())
                    .manageremail(account.getManageremail())
                    .build();
            getAccountSearchList.add(accountDTO);
        }
        return getAccountSearchList;

    }


}
