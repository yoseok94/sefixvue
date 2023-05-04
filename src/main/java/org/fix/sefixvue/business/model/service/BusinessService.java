package org.fix.sefixvue.business.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.business.entity.AccountRepository;
import org.fix.sefixvue.business.entity.PlanDisbursementRepository;
import org.fix.sefixvue.business.entity.TradeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class BusinessService {
    private final AccountRepository accountRepository;
    private final PlanDisbursementRepository planDisbursementRepository;
    private final TradeRepository tradeRepository;
}
