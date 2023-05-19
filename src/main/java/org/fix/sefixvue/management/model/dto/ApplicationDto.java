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
    private long appno;
    private String appid;
    private String appname;
    private String appphone;
    private String appemail;
    private String appbirth;
    private String apphiredate;
    private String applevel;
    private String appstatus;
    private String appdeptname;
    private String appchange;
    private String appreason;
    private String appdate;
    private String appaccept;

}
