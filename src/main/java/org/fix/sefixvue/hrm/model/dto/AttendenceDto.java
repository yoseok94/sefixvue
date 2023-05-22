package org.fix.sefixvue.hrm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendenceDto {
    private long attendenceno;
    private String empId;
    private String empname;
    private String emplevel;
    private String deptname;
    private String intime;
    private String outtime;
    private String inip;
    private String outip;
    private String requestdate;
    private String reason;
    private String reasonpr;
    private String requestresult;
    private String divide;
}
