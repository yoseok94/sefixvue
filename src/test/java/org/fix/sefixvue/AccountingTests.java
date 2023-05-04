package org.fix.sefixvue;


import org.fix.sefixvue.accounting.entity.*;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // test에서 @Transactional을 사용하면 자동 롤백된다.
public class AccountingTests {
    @Autowired
    private AllowancesDeductionsRepository allowancesDeductionsRepository;
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private SlipstatementRepository slipstatementRepository;

    @Before
    public void beforeClass() {
        System.out.println("-----테스트 시작-----");
    }

    @After
    public void afterClass() {
        System.out.println("-----테스트 종료-----"); //logger 사용을 권장함
    }

    @org.junit.Test
    public void AllowancesDeductionTest() {
        List<AllowancesDeductions> list = allowancesDeductionsRepository.findAll();

        for(AllowancesDeductions allowancesDeductions : list){
            System.out.println(allowancesDeductions);
        }
    }

    @org.junit.Test
    public void SalaryTest() {
        List<Salary> list2 = salaryRepository.findAll();

        for(Salary salary : list2){
            System.out.println(salary);
        }

    }

    @org.junit.Test
    public void SlipstatementTest() {
        List<Slipstatement> list3 = slipstatementRepository.findAll();

        for(Slipstatement slipstatement : list3){
            System.out.println(slipstatement);
        }

    }

}
