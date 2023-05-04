package org.fix.sefixvue.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fix.sefixvue.management.entity.ApplicationRepository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationDto {
    private long app_no;
    private String app_id;
    private String app_name;
    private String app_phone;
    private String app_email;
    private java.sql.Date app_birth;
    private java.sql.Date app_hiredate;
    private int app_level;
    private String app_status;
    private String app_deptname;
    private String app_change;
    private String app_reason;
    private java.sql.Date app_date;
    private int app_accept;

}
