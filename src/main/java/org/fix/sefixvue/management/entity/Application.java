package org.fix.sefixvue.management.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder                              // 클래스로 컴파일 되라.
@Table(name = "Application")                // 자동으로 생성될 테이블 이름이 BOARD 이다
@Entity
public class Application {
    @Id                               // PK 처리
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "mySequence", sequenceName = "application_seq", allocationSize = 1)
    @Column(name = "app_no")
    private long appno;
    @Column(name = "app_id")
    private String appid;
    @Column(name = "app_name")
    private String appname;
    @Column(name = "app_phone")
    private String appphone;
    @Column(name = "app_email")
    private String appemail;
    @Column(name = "app_birth")
    private java.sql.Date appbirth;
    @Column(name = "app_hiredate")
    private java.sql.Date apphiredate;
    @Column(name = "app_level")
    private int applevel;
    @Column(name = "app_status")
    private String appstatus;
    @Column(name = "app_deptname")
    private String appdeptname;
    @Column(name = "app_change")
    private String appchange;
    @Column(name = "app_reason")
    private String appreason;
    @Column(name = "app_date")
    private java.sql.Date appdate;
    @Column(name = "app_accept")
    private int appaccept;

}
