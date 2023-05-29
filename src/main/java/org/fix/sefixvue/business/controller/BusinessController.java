package org.fix.sefixvue.business.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.admin.entity.Product;
import org.fix.sefixvue.admin.entity.ProductRepository;
import org.fix.sefixvue.business.entity.Account;
import org.fix.sefixvue.business.model.dto.AccountDto;
import org.fix.sefixvue.business.model.dto.PlanDisbursementDto;
import org.fix.sefixvue.business.model.service.BusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class BusinessController {
    private final BusinessService businessService;

    // 영업계획 목록 가져오기
    @GetMapping("/business/planlist")
    public List<PlanDisbursementDto> getPlanList() {
        return businessService.getPlanList();
    }

    //영업계획 등록
    @PostMapping("/business/planadd")
    public ResponseEntity<String> addPlan(@RequestBody PlanDisbursementDto planDto) {
        try {
            int purchaseprice = businessService.getProductPurchasePrice(planDto.getProductno());

            // 상품 금액 계산
            int consumerprice = planDto.getTargetquantity() * purchaseprice;
            int targetamount = (int) (consumerprice + consumerprice * 0.1);

            planDto.setTargetamount(targetamount);

            if (planDto.getDisbursementdetail() == null) {
                planDto.setDisbursementdetail(" ");
            }

            businessService.addPlan(planDto);
            return ResponseEntity.ok("영업계획이 등록되었습니다.");
        } catch (Exception e) {
            log.error("Failed to add plan", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("영업계획 등록에 실패했습니다.");
        }
    }

    @GetMapping("/business/products")
    public List<Product> getProducts() {
        return businessService.getProducts();
    }

    @GetMapping("/business/product/{productno}")
    public ResponseEntity<String> getProductByName(@PathVariable Long productno) {
        try {
            String productname = businessService.getProductByName(productno);
            return ResponseEntity.ok(productname);
        } catch (Exception e) {
            log.error("Failed to get product name", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상품명 조회에 실패했습니다.");
        }
    }

    @GetMapping("/business/accounts")
    public List<AccountDto> getAccounts() {
        try {
            List<Account> accounts = businessService.getAccounts();
            List<AccountDto> accountDtos = new ArrayList<>();
            for (Account account : accounts) {
                AccountDto accountDto = AccountDto.builder()
                        .accountno(account.getAccountno())
                        .accountname(account.getAccountname())
                        .build();
                accountDtos.add(accountDto);
            }
            return accountDtos;
        } catch (Exception e) {
            log.error("Failed to get accounts", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "거래처 목록 조회에 실패했습니다.");
        }
    }


    // 영업계획 수정
    @PatchMapping(value = "/business/planedit/{planno}")
    public ResponseEntity<String> editPlan(@PathVariable Long planno, @RequestBody PlanDisbursementDto planDto) {

        if (planno == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 계획 번호입니다.");
        }
        try {
            int purchaseprice = businessService.getProductPurchasePrice(planDto.getProductno());

            // 상품 금액 계산
            int consumerprice = planDto.getTargetquantity() * purchaseprice;
            int targetamount = (int) (consumerprice + consumerprice * 0.1);

            planDto.setTargetamount(targetamount);

            businessService.editPlan(planno, planDto);
            return ResponseEntity.ok("영업계획이 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            log.error("Plan with planno {} not found", planno, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("영업계획을 찾을 수 없습니다.");
        } catch (Exception e) {
            log.error("Failed to edit plan with planno: {}", planno, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("영업계획 수정에 실패했습니다.");
        }
    }

    // 영업계획 삭제
    @DeleteMapping("/business/plandel")
    public ResponseEntity<String> deletePlans(@RequestBody List<Long> planno) {
        try {
            businessService.deletePlans(planno);
            return ResponseEntity.ok("영업계획이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("영업계획 삭제에 실패했습니다.");
        }
    }


    // 영업계획 상세 조회
    @GetMapping("/business/plan/{planno}")
    public ResponseEntity<?> getPlanById(@PathVariable Long planno) {
        try {
            PlanDisbursementDto planDto = businessService.getPlanById(planno);
            return ResponseEntity.ok(planDto);
        } catch (IllegalArgumentException e) {
            log.error("Plan with planno {} not found", planno, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("영업계획을 찾을 수 없습니다.");
        } catch (Exception e) {
            log.error("Failed to get plan with planno: {}", planno, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("영업계획 조회에 실패했습니다.");
        }
    }

    //영업 지출 결의서 목록 가져오기
    @GetMapping("/business/disbursementlist")
    public List<PlanDisbursementDto> getDisbursementList() {
        return businessService.getDisbursementList();
    }


    // 영업 지출 등록/수정
    @PatchMapping("/business/disbursementedit/{planno}")
    public ResponseEntity<String> editDisbursement(@PathVariable Long planno, @RequestBody PlanDisbursementDto disbursementDto) {
        try {
            int purchaseprice = businessService.getProductPurchasePrice(disbursementDto.getProductno());

            // 상품 금액 계산
            int dconsumerprice = disbursementDto.getQuantity() * purchaseprice;
            int disbursementtax = dconsumerprice/10;
            int totalamount = (int) (dconsumerprice + disbursementtax);

            disbursementDto.setDisbursementsupplyprice(dconsumerprice);
            disbursementDto.setDisbursementtax(disbursementtax);
            disbursementDto.setTotalamount(totalamount);

            businessService.editDisbursement(planno, disbursementDto);
            return ResponseEntity.ok("영업지출이 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            log.error("Disbursement with planno {} not found", planno, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("영업지출을 찾을 수 없습니다.");
        } catch (Exception e) {
            log.error("Failed to edit disbursement with planno: {}", planno, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("영업지출 수정에 실패했습니다.");
        }
    }



    // 영업지출 상세 조회
    @GetMapping("/business/disbursement/{planno}")
    public ResponseEntity<?> getDisbursementById(@PathVariable Long planno) {
        try {
            PlanDisbursementDto disbursementDto = businessService.getDisbursementById(planno);
            return ResponseEntity.ok(disbursementDto);
        } catch (IllegalArgumentException e) {
            log.error("Disbursement with planno {} not found", planno, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("영업지출을 찾을 수 없습니다.");
        } catch (Exception e) {
            log.error("Failed to get disbursement with planno: {}", planno, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("영업지출 조회에 실패했습니다.");
        }
    }

    // 영업지출 삭제
    @PatchMapping(value = "/business/disbursementdel/{planno}")
    public ResponseEntity<String> deleteDisbursement(@PathVariable Long planno ) {

        if (planno == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 결의서 번호입니다.");
        }
        try {
            businessService.deleteDisbursement(planno);
            return ResponseEntity.ok("영업지출이 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            log.error("Disbursement with planno {} not found", planno, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("영업지출을 찾을 수 없습니다.");
        } catch (Exception e) {
            log.error("Failed to delete disbursement with planno: {}", planno, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("영업지출 삭제에 실패했습니다.");
        }
    }

    //영업실적 목록 가져오기
    @GetMapping("/business/performancelist")
    public List<PlanDisbursementDto> getPerformanceList() {
        return businessService.getPerformanceList();
    }


    // 거래처 리스트 목록 출력
    @GetMapping("/businessPartnerList")
    public List<AccountDto> accountList(){
        return businessService.getAccountList();
    }

    //
    @PostMapping("/businessPartnerAdd")
    public Account insert(@RequestBody AccountDto accountDto) {
        return businessService.insert(accountDto);
    }
    @PatchMapping("/businessPartnerEdit")
    public Account update(@RequestBody AccountDto accountDto) {

        return businessService.update(accountDto);
    }
    @DeleteMapping("/businessPartnerList/{accountno}")
    public void delete(@PathVariable Long accountno){
        businessService.delete(accountno);
    }

    // 거래처명으로 조회
    @GetMapping("/businessPartnerList/search")
    public List<AccountDto> getAccountSearchList(@RequestParam String searchName) {
        return businessService.findByAccountNameContaining(searchName);
    }
}
