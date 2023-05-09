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
    private long empno;
    private String empid;
    private String emppw;
    private String empname;
    private String empphone;
    private String empaddress;
    private String empemail;
    private java.sql.Date empbirth;
    private java.sql.Date emphiredate;
    private int emplevel;
    private String empstatus;
    private String deptname;
    private int empannual;
    private String empprofile;
}
