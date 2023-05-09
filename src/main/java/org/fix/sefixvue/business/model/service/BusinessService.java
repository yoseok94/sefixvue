package org.fix.sefixvue.business.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.business.entity.Account;
import org.fix.sefixvue.business.entity.AccountRepository;
import org.fix.sefixvue.business.entity.PlanDisbursementRepository;
import org.fix.sefixvue.business.entity.TradeRepository;
import org.fix.sefixvue.business.model.dto.AccountDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class BusinessService {
    private final AccountRepository accountRepository;
    private final PlanDisbursementRepository planDisbursementRepository;
    private final TradeRepository tradeRepository;

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
