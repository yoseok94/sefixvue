package org.fix.sefixvue.business.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.business.entity.AccountRepository;
import org.fix.sefixvue.business.entity.PlanDisbursementRepository;
import org.fix.sefixvue.business.entity.TradeRepository;
import org.fix.sefixvue.hrm.entity.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class BusinessService {
    private final AccountRepository accountRepository;
    private final PlanDisbursementRepository planDisbursementRepository;
    private final TradeRepository tradeRepository;
}
