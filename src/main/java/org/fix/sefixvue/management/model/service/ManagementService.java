package org.fix.sefixvue.management.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.Pagination;
import org.fix.sefixvue.common.SearchCondition;
import org.fix.sefixvue.hrm.entity.Employee;
import org.fix.sefixvue.hrm.entity.EmployeeRepository;
import org.fix.sefixvue.hrm.entity.EmployeeRepositoryCustom;
import org.fix.sefixvue.hrm.model.dto.EmployeeDto;
import org.fix.sefixvue.management.entity.Application;
import org.fix.sefixvue.management.entity.ApplicationRepository;
import org.fix.sefixvue.management.entity.ApplicationRepositoryCustom;
import org.fix.sefixvue.management.model.dto.ApplicationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ManagementService {
    private final ApplicationRepositoryCustom applicationRepositoryCustom;
    private final ApplicationRepository applicationRepository;
    private final EmployeeRepository employeeRepository;

    public Header<List<ApplicationDto>> getApplicationList(Pageable pageable, SearchCondition searchCondition) {

        List<ApplicationDto> applicationDtos = new ArrayList<>();

        Page<Application> applications = applicationRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        for (Application application : applications) {
            ApplicationDto applicationDto = ApplicationDto.builder()
                    .appno(application.getAppno())
                    .appid(application.getAppid())
                    .appname(application.getAppname())
                    .appphone(application.getAppphone())
                    .appemail(application.getAppemail())
                    .appbirth(application.getAppbirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .apphiredate(application.getApphiredate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .applevel(application.getApplevel())
                    .appstatus(application.getAppstatus())
                    .appdeptname(application.getAppdeptname())
                    .appchange(application.getAppchange())
                    .appreason(application.getAppreason())
                    .appdate(application.getAppdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .appaccept(application.getAppaccept())
                    .build();

            applicationDtos.add(applicationDto);
        }

        Pagination pagination = new Pagination(
                (int) applications.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );

        return Header.OK(applicationDtos, pagination);
    }


    public EmployeeDto getEmpApp(Long empno) {  // 개별정보보기
        Employee entity = employeeRepository.findById(empno).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        // findById(id) 하면 SELECT + WHERE 작동됨. 람다 표현식 사용됨. 의미는 ~~ 조건을 만족시켰을 떄 에러 발생시켜라
        return EmployeeDto.builder()
                .empId(entity.getEmpId())
                .empname(entity.getEmpname())
                .empphone(entity.getEmpphone())
                .empaddress(entity.getEmpaddress())
                .empemail(entity.getEmpemail())
                .empbirth(entity.getEmpbirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .emphiredate(entity.getEmphiredate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .deptname(entity.getDeptname())
                .emplevel(entity.getEmplevel())
                .empstatus(entity.getEmpstatus())
                .build();
        // 컬럼값 하나씩 꺼내서 매핑 -> DTO 객체 리턴
    }


    public ApplicationDto getApp(Long appno) {  // 개별정보보기
        Application entity = applicationRepository.findById(appno).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        // findById(id) 하면 SELECT + WHERE 작동됨. 람다 표현식 사용됨. 의미는 ~~ 조건을 만족시켰을 떄 에러 발생시켜라
        return ApplicationDto.builder()
                .appid(entity.getAppid())
                .appname(entity.getAppname())
                .appphone(entity.getAppphone())
                .appemail(entity.getAppemail())
                .appbirth(entity.getAppbirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .apphiredate(entity.getApphiredate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .applevel(entity.getApplevel())
                .appstatus(entity.getAppstatus())
                .appdeptname(entity.getAppdeptname())
                .appchange(entity.getAppchange())
                .appreason(entity.getAppreason())
                .appdate(entity.getAppdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .appaccept(entity.getAppaccept())
                .build();
        // 컬럼값 하나씩 꺼내서 매핑 -> DTO 객체 리턴
    }

    public Application create(ApplicationDto applicationDto){
        Application entity = Application.builder()
                .appid(applicationDto.getAppid())
                .appname(applicationDto.getAppname())
                .apphiredate(LocalDateTime.now())
                .appdeptname(applicationDto.getAppdeptname())
                .applevel(applicationDto.getApplevel())
                .appchange(applicationDto.getAppchange())
                .appreason(applicationDto.getAppreason())
                .build();
        return applicationRepository.save(entity);
    }


}
