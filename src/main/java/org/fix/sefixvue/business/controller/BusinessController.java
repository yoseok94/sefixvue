package org.fix.sefixvue.business.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.business.entity.Account;
import org.fix.sefixvue.business.model.dto.AccountDto;
import org.fix.sefixvue.business.model.service.BusinessService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class BusinessController {
    private final BusinessService businessService;

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
