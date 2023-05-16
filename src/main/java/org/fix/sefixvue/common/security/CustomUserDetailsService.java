package org.fix.sefixvue.common.security;

import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.common.security.domain.CustomUser;
import org.fix.sefixvue.hrm.entity.Employee;
import org.fix.sefixvue.hrm.entity.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException {
        log.info("Load User By UserName : " + empId);

        Employee employee = employeeRepository.findByEmpId(empId).get(0);

        log.info("member : " + employee);

        return employee == null ? null : new CustomUser(employee);
    }
}
