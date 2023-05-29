package org.fix.sefixvue.hrm.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.admin.entity.Dept;
import org.fix.sefixvue.admin.entity.DeptRepository;
import org.fix.sefixvue.admin.model.dto.DeptDto;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class HrmService implements UserDetailsService {
    private final EmployeeRepositoryCustom employeeRepositoryCustom;
    private final EmployeeRepository employeeRepository;
    private final AttendenceRepository attendenceRepository;
    private final AttendenceRepositoryCustom attendenceRepositoryCustom;
    private final DeptRepository deptRepository;

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
                    .emplevel(employee.getEmplevel())
                    .emphiredate(employee.getEmphiredate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
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

    public Header<List<AttendenceDto>> getAttendenceList(Pageable pageable, SearchCondition searchCondition) {
        List<AttendenceDto> attendenceDtos = new ArrayList<>();

        Page<Attendence> attendences = attendenceRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        for (Attendence attendence : attendences) {
            if(attendence.getIntime() != null && attendence.getOuttime() != null && attendence.getRequestdate() != null) {
                AttendenceDto attendenceDto = AttendenceDto.builder()
                        .attendenceno(attendence.getAttendenceno())
                        .empId(attendence.getEmpId())
                        .empname(attendence.getEmpname())
                        .emplevel(attendence.getEmplevel())
                        .reason(attendence.getReason())
                        .reasonpr(attendence.getReasonpr())
                        .deptname(attendence.getDeptname())
                        .divide(attendence.getDivide())
                        .intime(attendence.getIntime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                        .outtime(attendence.getOuttime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                        .requestdate(attendence.getRequestdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .requestresult(attendence.getRequestresult())
                        .build();
                attendenceDtos.add(attendenceDto);
            }else if(attendence.getIntime() != null && attendence.getOuttime() != null && attendence.getRequestdate() == null){
                AttendenceDto attendenceDto = AttendenceDto.builder()
                        .attendenceno(attendence.getAttendenceno())
                        .empId(attendence.getEmpId())
                        .empname(attendence.getEmpname())
                        .emplevel(attendence.getEmplevel())
                        .deptname(attendence.getDeptname())
                        .divide(attendence.getDivide())
                        .intime(attendence.getIntime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                        .outtime(attendence.getOuttime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                        .requestresult(attendence.getRequestresult())
                        .build();
                attendenceDtos.add(attendenceDto);
            }else if(attendence.getRequestdate() != null){
                AttendenceDto attendenceDto = AttendenceDto.builder()
                        .attendenceno(attendence.getAttendenceno())
                        .empId(attendence.getEmpId())
                        .empname(attendence.getEmpname())
                        .emplevel(attendence.getEmplevel())
                        .reason(attendence.getReason())
                        .reasonpr(attendence.getReasonpr())
                        .deptname(attendence.getDeptname())
                        .divide(attendence.getDivide())
                        .requestdate(attendence.getRequestdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .requestresult(attendence.getRequestresult())
                        .build();
                attendenceDtos.add(attendenceDto);
            }
        }

        Pagination pagination = new Pagination(
                (int) attendences.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );

        return Header.OK(attendenceDtos, pagination);
    }

    public Header<List<AttendenceDto>> getAttendenceList2(Pageable pageable, SearchCondition searchCondition) {
        List<AttendenceDto> attendenceDtos = new ArrayList<>();

        Page<Attendence> attendences = attendenceRepositoryCustom.findAllBySearchCondition2(pageable, searchCondition);
        if(searchCondition.getSv() == null || searchCondition.getSv().equals("")){
            for (Attendence attendence : attendences) {
                AttendenceDto attendenceDto = AttendenceDto.builder()
                        .attendenceno(attendence.getAttendenceno())
                        .empId(attendence.getEmpId())
                        .empname(attendence.getEmpname())
                        .emplevel(attendence.getEmplevel())
                        .reason(attendence.getReason())
                        .reasonpr(attendence.getReasonpr())
                        .deptname(attendence.getDeptname())
                        .divide(attendence.getDivide())
                        .requestdate(attendence.getRequestdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .requestresult(attendence.getRequestresult())
                        .build();
                attendenceDtos.add(attendenceDto);
            }
        }else{
            for (Attendence attendence : attendences) {
                if(attendence.getEmpId().contains(searchCondition.getSv()) && searchCondition.getSk().equals("ID")) {
                    AttendenceDto attendenceDto = AttendenceDto.builder()
                            .attendenceno(attendence.getAttendenceno())
                            .empId(attendence.getEmpId())
                            .empname(attendence.getEmpname())
                            .emplevel(attendence.getEmplevel())
                            .reason(attendence.getReason())
                            .reasonpr(attendence.getReasonpr())
                            .deptname(attendence.getDeptname())
                            .divide(attendence.getDivide())
                            .requestdate(attendence.getRequestdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                            .requestresult(attendence.getRequestresult())
                            .build();
                    attendenceDtos.add(attendenceDto);
                }else if(attendence.getEmpname().contains(searchCondition.getSv()) && searchCondition.getSk().equals("Name")) {
                    AttendenceDto attendenceDto = AttendenceDto.builder()
                            .attendenceno(attendence.getAttendenceno())
                            .empId(attendence.getEmpId())
                            .empname(attendence.getEmpname())
                            .emplevel(attendence.getEmplevel())
                            .reason(attendence.getReason())
                            .reasonpr(attendence.getReasonpr())
                            .deptname(attendence.getDeptname())
                            .divide(attendence.getDivide())
                            .requestdate(attendence.getRequestdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                            .requestresult(attendence.getRequestresult())
                            .build();
                    attendenceDtos.add(attendenceDto);
                }
            }
        }

        Pagination pagination = new Pagination(
                (int) attendences.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );

        return Header.OK(attendenceDtos, pagination);
    }
    public Employee updateEmpStatus(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getEmpno()).orElseThrow(() -> new RuntimeException("사원 정보를 찾을 수 없습니다."));
        employee.setEmpstatus(employeeDto.getEmpstatus());
        return employeeRepository.save(employee);
    }

    public Employee update(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getEmpno()).orElseThrow(() -> new RuntimeException("사원 정보를 찾을 수 없습니다."));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        employee.setEmppw(passwordEncoder.encode(employeeDto.getEmppw()));
        employee.setEmpname(employeeDto.getEmpname());
        employee.setEmpaddress(employeeDto.getEmpaddress());
        employee.setEmpemail(employeeDto.getEmpemail());
        employee.setEmplevel(employeeDto.getEmplevel());
        employee.setDeptname(employeeDto.getDeptname());
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
                .emplevel(attendenceDto.getEmplevel())
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
        if(attendence.getRequestdate() == null || attendence.getIntime() == null){
            attendence.setRequestdate(LocalDate.now());
        }

        if(attendence.getIntime() == null) {
            return AttendenceDto.builder()
                    .attendenceno(attendence.getAttendenceno())
                    .empId(attendence.getEmpId())
                    .empname(attendence.getEmpname())
                    .emplevel(attendence.getEmplevel())
                    .deptname(attendence.getDeptname())
                    .requestdate(attendence.getRequestdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .requestresult(attendence.getRequestresult())
                    .build();
        }else if(attendence.getOuttime() == null) {
            return AttendenceDto.builder()
                    .attendenceno(attendence.getAttendenceno())
                    .empId(attendence.getEmpId())
                    .empname(attendence.getEmpname())
                    .emplevel(attendence.getEmplevel())
                    .deptname(attendence.getDeptname())
                    .requestdate(attendence.getIntime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .requestresult(attendence.getRequestresult())
                    .intime(attendence.getIntime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .inip(attendence.getInip())
                    .build();
        }else{
            return AttendenceDto.builder()
                    .attendenceno(attendence.getAttendenceno())
                    .empId(attendence.getEmpId())
                    .empname(attendence.getEmpname())
                    .emplevel(attendence.getEmplevel())
                    .deptname(attendence.getDeptname())
                    .requestdate(attendence.getIntime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .requestresult(attendence.getRequestresult())
                    .intime(attendence.getIntime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .inip(attendence.getInip())
                    .outtime(attendence.getOuttime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .outip(attendence.getOutip())
                    .divide(attendence.getDivide())
                    .build();
        }
    }

    public Attendence updateAttendence(AttendenceDto attendenceDto) {
        Attendence attendence = attendenceRepository.findById(attendenceDto.getAttendenceno()).orElseThrow(() -> new RuntimeException("근태 정보를 찾을 수 없습니다."));
        attendence.setRequestresult(attendenceDto.getRequestresult());
        return attendenceRepository.save(attendence);
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
            long intime = sdf.parse(attendence.getIntime().format(DateTimeFormatter.ofPattern("HH:mm"))).getTime();
            long outtime = sdf.parse(attendence.getOuttime().format(DateTimeFormatter.ofPattern("HH:mm"))).getTime();

            long result = (outtime - intime)/1000;
            log.info(String.valueOf(result));
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
                .emplevel(attendenceDto.getEmplevel())
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

    public Employee createNewMember(EmployeeDto employeeDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Employee employee = Employee.builder()
                .empId(employeeDto.getEmpId())
                .emppw(passwordEncoder.encode(employeeDto.getEmppw()))
                .empname(employeeDto.getEmpname())
                .empphone(employeeDto.getEmpphone())
                .empaddress(employeeDto.getEmpaddress())
                .empemail(employeeDto.getEmpemail())
                .empstatus("N")
                .empbirth(LocalDate.parse(employeeDto.getEmpbirth(), formatter))
                .emphiredate(LocalDate.parse(employeeDto.getEmphiredate(), formatter))
                .deptname(employeeDto.getDeptname())
                .emplevel(employeeDto.getEmplevel())
                .build();

        return employeeRepository.save(employee);
    }

    public List<DeptDto> getDeptList() {
        List<Dept> deptlist = deptRepository.findAll();
        List<DeptDto> list = new ArrayList<>();

        for(Dept entity : deptlist){
            DeptDto deptDto = DeptDto.builder().deptno(entity.getDeptno()).deptname(entity.getDeptname()).build();
            list.add(deptDto);
        }

        log.info(list.toString());

        return list;
    }


    class dateComparator implements Comparator<Attendence> {
        @Override
        public int compare(Attendence a1, Attendence a2) {
            if(a1.getRequestdate() == null){
                a1.setRequestdate(LocalDate.now());
            }
            if(a2.getRequestdate() == null){
                a2.setRequestdate(LocalDate.now());
            }
            if (a1.getRequestdate().isAfter(a2.getRequestdate())) {
                return 1;
            } else if (a1.getRequestdate().isBefore(a2.getRequestdate())) {
                return -1;
            }
            return 0;
        }
    }

    public List<AttendenceDto> getmylist(String empId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate = LocalDate.now().withDayOfMonth(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String enddate = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate startDate2 = LocalDate.parse(startDate, formatter);
        LocalDate endDate2 = LocalDate.parse(enddate, formatter);

        Sort sort = sortByAttendenceno();
        List<Attendence> slist = attendenceRepository.findAll(sort);

        List<Attendence> attendenceDateList = new ArrayList<>();
        for(Attendence ad: slist){
            if(ad.getEmpId().equals(empId)){
                attendenceDateList.add(ad);
            }
        }

        Collections.sort(attendenceDateList, new dateComparator());

        List<Attendence> attendenceList = new ArrayList<>();
        for(Attendence entity: attendenceDateList){
          if(entity.getRequestdate() != null && !entity.getRequestdate().isBefore(startDate2) && !entity.getRequestdate().isAfter(endDate2)){
              attendenceList.add(entity);
          }
        }


        List<AttendenceDto> list = new ArrayList<>();

        for(Attendence attendence : attendenceList){
            if(attendence.getReason() == null){
                attendence.setReason(" ");
            }
            if(attendence.getIntime() != null && attendence.getOuttime() != null) {
                AttendenceDto attendencedto = AttendenceDto.builder()
                        .attendenceno(attendence.getAttendenceno())
                        .requestdate(attendence.getIntime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .intime(attendence.getIntime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                        .outtime(attendence.getOuttime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                        .reason(attendence.getReason())
                        .divide(attendence.getDivide())
                        .build();
                list.add(attendencedto);
            }else if(attendence.getIntime() == null && attendence.getReason() != null){
                AttendenceDto attendencedto = AttendenceDto.builder()
                        .attendenceno(attendence.getAttendenceno())
                        .requestdate(attendence.getRequestdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .reason(attendence.getReason())
                        .divide(attendence.getDivide())
                        .build();
                list.add(attendencedto);
            }

        }

        return list;
    }
}
