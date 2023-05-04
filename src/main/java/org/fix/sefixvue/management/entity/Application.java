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
    private long app_no;
    @Column(name = "app_id")
    private String app_id;
    @Column(name = "app_name")
    private String app_name;
    @Column(name = "app_phone")
    private String app_phone;
    @Column(name = "app_email")
    private String app_email;
    @Column(name = "app_birth")
    private java.sql.Date app_birth;
    @Column(name = "app_hiredate")
    private java.sql.Date app_hiredate;
    @Column(name = "app_level")
    private int app_level;
    @Column(name = "app_status")
    private String app_status;
    @Column(name = "app_deptname")
    private String app_deptname;
    @Column(name = "app_change")
    private String app_change;
    @Column(name = "app_reason")
    private String app_reason;
    @Column(name = "app_date")
    private java.sql.Date app_date;
    @Column(name = "app_accept")
    private int app_accept;

}
