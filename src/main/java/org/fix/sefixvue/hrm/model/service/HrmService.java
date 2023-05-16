package org.fix.sefixvue.hrm.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.SearchCondition;
import org.fix.sefixvue.common.Pagination;
import org.fix.sefixvue.hrm.entity.AttendenceRepository;
import org.fix.sefixvue.hrm.entity.Employee;
import org.fix.sefixvue.hrm.entity.EmployeeRepository;
import org.fix.sefixvue.hrm.entity.EmployeeRepositoryCustom;
import org.fix.sefixvue.hrm.model.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class HrmService {
    private final EmployeeRepositoryCustom employeeRepositoryCustom;
    private final EmployeeRepository employeeRepository;
    private final AttendenceRepository attendenceRepository;

    public Header<List<EmployeeDto>> getEmployeeList(Pageable pageable, SearchCondition searchCondition) {
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        Page<Employee> employees = employeeRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        for (Employee employee : employees) {
            EmployeeDto employeeDto = EmployeeDto.builder()
                    .empno(employee.getEmpno())
                    .empid(employee.getEmpId())
                    .empname(employee.getEmpname())
                    .empstatus(employee.getEmpstatus())
                    .deptname(employee.getDeptname())
                    .empstatus(employee.getEmpstatus())
                    .build();

            employeeDtos.add(employeeDto);
        }

        Pagination pagination = new Pagination(
                (int) employees.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );

        return Header.OK(employeeDtos, pagination);
    }

    public Employee updateEmpStatus(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getEmpno()).orElseThrow(() -> new RuntimeException("사원 정보를 찾을 수 없습니다."));
        employee.setEmpstatus(employeeDto.getEmpstatus());
        return employeeRepository.save(employee);
    }

    public Employee update(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getEmpno()).orElseThrow(() -> new RuntimeException("사원 정보를 찾을 수 없습니다."));
        //employee.setEmpstatus(employeeDto.getEmpstatus());
        return employeeRepository.save(employee);
    }

    public EmployeeDto getEmployee(Long empno) {
        Employee employee = employeeRepository.findById(empno).orElseThrow(() -> new RuntimeException("사원 정보를 찾을 수 없습니다."));
        return EmployeeDto.builder()
                .empno(employee.getEmpno())
                .empid(employee.getEmpId())
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
                .empannual(employee.getEmpannual())
                .empprofile(employee.getEmpprofile())
                .build();
    }

    public void delete(Long empno) {
        Employee employee = employeeRepository.findById(empno).orElseThrow(() -> new RuntimeException("사원 정보를 찾을 수 없습니다."));
        employeeRepository.delete(employee);
    }
}
