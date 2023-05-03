package org.fix.sefixvue.hrm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    private long emp_no;
    private String emp_id;
    private String emp_pw;
    private String emp_name;
    private String emp_phone;
    private String emp_address;
    private String emp_email;
    private java.sql.Date emp_birth;
    private java.sql.Date emp_hiredate;
    private int emp_level;
    private String emp_status;
    private String dept_name;
    private int emp_annual;
    private String emp_profile;
}
