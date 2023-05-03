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
    private Long account_no;
    private String account_name;
    private String representative_name;
    private String account_phone;
    private String account_fax;
    private String account_address;
    private String manager_name;
    private String manager_phone;
    private String manager_email;
}
