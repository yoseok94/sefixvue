package org.fix.sefixvue.accounting.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.accounting.entity.AllowancesDeductionRepository;
import org.fix.sefixvue.accounting.entity.SalaryRepository;
import org.fix.sefixvue.accounting.entity.SlipstatementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class AccountingService {

    private final AllowancesDeductionRepository allowancesDeductionRepository;
    private final SalaryRepository salaryRepository;
    private final SlipstatementRepository slipstatementRepository;
}
