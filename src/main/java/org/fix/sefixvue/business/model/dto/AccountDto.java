package org.fix.sefixvue.business.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private long accountno;
    private String accountname;
    private String representativename;
    private String accountphone;
    private String accountfax;
    private String accountaddress;
    private String managername;
    private String managerphone;
    private String manageremail;
}
