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
    private long account_no;
    @Column(name = "account_name")
    private String account_name;
    @Column(name = "representative_name")
    private String representative_name;
    @Column(name = "account_phone")
    private String account_phone;
    @Column(name = "account_fax")
    private String account_fax;
    @Column(name = "account_address")
    private String account_address;
    @Column(name = "manager_name")
    private String manager_name;
    @Column(name = "manager_phone")
    private String manager_phone;
    @Column(name = "manager_email")
    private String manager_email;
}
