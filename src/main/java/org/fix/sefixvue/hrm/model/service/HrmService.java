package org.fix.sefixvue.hrm.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.SearchCondition;
import org.fix.sefixvue.common.Pagination;
import org.fix.sefixvue.hrm.entity.*;
import org.fix.sefixvue.hrm.model.dto.AttendenceDto;
import org.fix.sefixvue.hrm.model.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class HrmService implements UserDetailsService {
    private final EmployeeRepositoryCustom employeeRepositoryCustom;
    private final EmployeeRepository employeeRepository;
    private final AttendenceRepository attendenceRepository;

    public Header<List<EmployeeDto>> getEmployeeList(Pageable pageable, SearchCondition searchCondition) {
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        Page<Employee> employees = employeeRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        for (Employee employee : employees) {
            EmployeeDto employeeDto = EmployeeDto.builder()
                    .empno(employee.getEmpno())
                    .empId(employee.getEmpId())
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

        return employeeRepository.save(employee);
    }

    public EmployeeDto getEmployee(Long empno) {
        Employee employee = employeeRepository.findById(empno).orElseThrow(() -> new RuntimeException("사원 정보를 찾을 수 없습니다."));
        return EmployeeDto.builder()
                .empno(employee.getEmpno())
                .emppw(employee.getEmppw())
                .empId(employee.getEmpId())
                .empname(employee.getEmpname())
                .empphone(employee.getEmpphone())
                .empaddress(employee.getEmpaddress())
                .empemail(employee.getEmpemail())
                .empbirth(employee.getEmpbirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .emphiredate(employee.getEmphiredate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .emplevel(employee.getEmplevel())
                .empstatus(employee.getEmpstatus())
                .deptname(employee.getDeptname())
                .empprofile(employee.getEmpprofile())
                .build();
    }

    public void delete(Long empno) {
        Employee employee = employeeRepository.findById(empno).orElseThrow(() -> new RuntimeException("사원 정보를 찾을 수 없습니다."));
        employeeRepository.delete(employee);
    }

    public Employee read(Long empno) throws Exception {
        return employeeRepository.getOne(empno);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findByEmpId(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Employee employee) {
        return User.builder()
                .username(employee.getEmpId())
                .password(employee.getEmppw())
                .roles(employee.getEmplevel())
                .build();
    }

    public EmployeeDto getEmployeeInfo(String empId) {
        Employee employee = employeeRepository.findByEmpId(empId).orElseThrow(() -> new RuntimeException("사원 정보를 찾을 수 없습니다."));
        return EmployeeDto.builder()
                .empno(employee.getEmpno())
                .emppw(employee.getEmppw())
                .empId(employee.getEmpId())
                .empname(employee.getEmpname())
                .empphone(employee.getEmpphone())
                .empaddress(employee.getEmpaddress())
                .empemail(employee.getEmpemail())
                .empbirth(employee.getEmpbirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .emphiredate(employee.getEmphiredate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .emplevel(employee.getEmplevel())
                .empstatus(employee.getEmpstatus())
                .deptname(employee.getDeptname())
                .empprofile(employee.getEmpprofile())
                .build();
    }

    public Attendence createAttendence(AttendenceDto attendenceDto) {
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        }
        catch ( UnknownHostException e ) {
            e.printStackTrace();
        }

        Attendence attendence = Attendence.builder()
                .empId(attendenceDto.getEmpId())
                .empname(attendenceDto.getEmpname())
                .deptname(attendenceDto.getDeptname())
                .requestresult("N")
                .intime(LocalDateTime.now())
                .inip(String.valueOf(ip))
                .build();
        return attendenceRepository.save(attendence);
    }

    private Sort sortByAttendenceno() {
        return Sort.by(Sort.Direction.DESC, "attendenceno");
    }

    public AttendenceDto checkInfo(String empId) {
        Sort sort = sortByAttendenceno();
        List<Attendence> list = attendenceRepository.findAll(sort);
        Attendence attendence = new Attendence();
        for(Attendence attendenceCopy : list){
            if(attendenceCopy.getEmpId().equals(empId)){
                attendence = attendenceCopy;
                break;
            }
        }
        if(attendence.getRequestdate() == null){
            attendence.setRequestdate(LocalDate.now());
        }

        if(attendence.getIntime() == null) {
            return AttendenceDto.builder()
                    .attendenceno(attendence.getAttendenceno())
                    .empId(attendence.getEmpId())
                    .empname(attendence.getEmpname())
                    .deptname(attendence.getDeptname())
                    .requestdate(attendence.getRequestdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .requestresult(attendence.getRequestresult())
                    .build();
        }else if(attendence.getOuttime() == null) {
            return AttendenceDto.builder()
                    .attendenceno(attendence.getAttendenceno())
                    .empId(attendence.getEmpId())
                    .empname(attendence.getEmpname())
                    .deptname(attendence.getDeptname())
                    .requestdate(attendence.getRequestdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .requestresult(attendence.getRequestresult())
                    .intime(attendence.getIntime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .inip(attendence.getInip())
                    .build();
        }else{
            return AttendenceDto.builder()
                    .attendenceno(attendence.getAttendenceno())
                    .empId(attendence.getEmpId())
                    .empname(attendence.getEmpname())
                    .deptname(attendence.getDeptname())
                    .requestdate(attendence.getRequestdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .requestresult(attendence.getRequestresult())
                    .intime(attendence.getIntime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .inip(attendence.getInip())
                    .outtime(attendence.getOuttime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .outip(attendence.getOutip())
                    .divide(attendence.getDivide())
                    .build();
        }
    }

    public Attendence update(AttendenceDto attendenceDto) {
        Attendence attendence = attendenceRepository.findById(attendenceDto.getAttendenceno()).orElseThrow(() -> new RuntimeException("근태 정보를 찾을 수 없습니다."));
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        }
        catch ( UnknownHostException e ) {
            e.printStackTrace();
        }

        attendence.setOuttime(LocalDateTime.now());
        attendence.setOutip(String.valueOf(ip));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            long intime = sdf.parse(attendence.getIntime().format(DateTimeFormatter.ofPattern("hh:mm"))).getTime();
            long outtime = sdf.parse(attendence.getOuttime().format(DateTimeFormatter.ofPattern("hh:mm"))).getTime();

            long result = (intime - outtime)/1000;
            if(result >= 28800 && result < 86400) {
                attendence.setDivide("정상");
            }else{
                attendence.setDivide("비정상");
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }

        return attendenceRepository.save(attendence);
    }

    public Attendence createOrder(AttendenceDto attendenceDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Attendence attendence = Attendence.builder()
                .empId(attendenceDto.getEmpId())
                .empname(attendenceDto.getEmpname())
                .deptname(attendenceDto.getDeptname())
                .requestresult(attendenceDto.getRequestresult())
                .reason(attendenceDto.getReason())
                .reasonpr(attendenceDto.getReasonpr())
                .requestdate(LocalDate.parse(attendenceDto.getRequestdate(), formatter))
                .build();
        return attendenceRepository.save(attendence);
    }

    public Attendence updateOrder(AttendenceDto attendenceDto) {
        Attendence attendence = attendenceRepository.findById(attendenceDto.getAttendenceno()).orElseThrow(() -> new RuntimeException("근태 정보를 찾을 수 없습니다."));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        attendence.setReason(attendenceDto.getReason());
        attendence.setReasonpr(attendenceDto.getReasonpr());
        attendence.setRequestdate(LocalDate.parse(attendenceDto.getRequestdate(), formatter));
        attendence.setRequestresult(attendenceDto.getRequestresult());

        return attendenceRepository.save(attendence);
    }
}
