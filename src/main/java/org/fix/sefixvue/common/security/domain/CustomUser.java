package org.fix.sefixvue.common.security.domain;


import org.fix.sefixvue.hrm.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUser extends User {
    private static final long serialVersionUID = 1L;

    private Employee employee;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(Employee entity) {
        // 사용자가 정의한 Member 타입을 스프링 시큐리티 UsersDetails 타입으로 변환한다.
        super(entity.getEmpId(), entity.getEmppw(),
                entity.getEmployeeList().stream()
                        .map(employee -> new SimpleGrantedAuthority(employee.getEmplevel()))
                        .collect(Collectors.toList()));

        this.employee = entity;
    }

    public Employee getMember() {
        return employee;
    }
}
