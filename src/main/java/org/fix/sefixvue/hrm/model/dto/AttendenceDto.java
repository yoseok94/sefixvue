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
    private String empid;
    private String empname;
    private java.sql.Timestamp intime;
    private java.sql.Timestamp outtime;
    private String inip;
    private String outip;
    private java.sql.Date requestdate;
    private String reason;
    private String reasonpr;
    private String requestresult;
    private String divide;
}
