package org.fix.sefixvue.business.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Account")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "account_seq", allocationSize = 1)
    @Column(name = "account_no")
    private long accountno;
    @Column(name = "account_name")
    private String accountname;
    @Column(name = "representative_name")
    private String representativename;
    @Column(name = "account_phone")
    private String accountphone;
    @Column(name = "account_fax")
    private String accountfax;
    @Column(name = "account_address")
    private String accountaddress;
    @Column(name = "manager_name")
    private String managername;
    @Column(name = "manager_phone")
    private String managerphone;
    @Column(name = "manager_email")
    private String manageremail;
}
