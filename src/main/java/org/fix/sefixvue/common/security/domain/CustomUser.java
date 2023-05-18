package org.fix.sefixvue.common.security.domain;


import org.fix.sefixvue.hrm.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class CustomUser extends User {
    private static final long serialVersionUID = 1L;

    private Employee employee;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(Employee entity) {
        super(entity.getEmpId(), entity.getEmppw(),
                Collections.singletonList(new SimpleGrantedAuthority(entity.getEmplevel())));
        this.employee = entity;
    }


    public Employee getMember() {
        return employee;
    }
}
