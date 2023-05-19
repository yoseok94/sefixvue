package org.fix.sefixvue.management.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder                              // 클래스로 컴파일 되라.
@Table(name = "Application")                // 자동으로 생성될 테이블 이름이 BOARD 이다
@Entity
public class Application {
    @Id                               // PK 처리
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private LocalDateTime appbirth;
    @Column(name = "app_hiredate")
    private LocalDateTime apphiredate;
    @Column(name = "app_level")
    private String applevel;
    @Column(name = "app_status")
    private String appstatus;
    @Column(name = "app_deptname")
    private String appdeptname;
    @Column(name = "app_change")
    private String appchange;
    @Column(name = "app_reason")
    private String appreason;
    @Column(name = "app_date")
    private LocalDateTime appdate;
    @Column(name = "app_accept")
    private String appaccept;

}
