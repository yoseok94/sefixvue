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
    private long empno;
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



//------------------------------부서신청파트----------------------------
//            ------------------APPLICATION------------------
//    CREATE TABLE APPLICATION(
//            APP_NO   NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
//    EMP_NO   NUMBER,
//    APP_ID      VARCHAR2(50),
//    APP_NAME      VARCHAR2(30)   NOT NULL,
//    APP_PHONE      VARCHAR2(30)   NOT NULL,
//    APP_EMAIL      VARCHAR2(100)   NOT NULL,
//    APP_BIRTH      DATE      NOT NULL,
//    APP_HIREDATE   DATE      NOT NULL,
//    APP_LEVEL      VARCHAR2(30)      NOT NULL,
//    APP_STATUS      CHAR(1)      DEFAULT 'N'   NOT NULL,
//    APP_DEPTNAME   VARCHAR2(30),
//    APP_CHANGE   VARCHAR2(30),
//    APP_REASON   VARCHAR2(300),
//    APP_DATE    DATE DEFAULT SYSDATE,
//    APP_ACCEPT          VARCHAR2(30)      DEFAULT '검토중',
//    CONSTRAINT PK_APPLICATION_NO PRIMARY KEY(APP_NO)
//);
//
//    COMMENT ON COLUMN APPLICATION.APP_NO is '부서신청자 순번';
//    COMMENT ON COLUMN APPLICATION.EMP_NO is '사원 순번';
//    COMMENT ON COLUMN APPLICATION.APP_ID is '부서신청자 아이디(사번)';
//    COMMENT ON COLUMN APPLICATION.APP_NAME is '부서신청자 이름';
//    COMMENT ON COLUMN APPLICATION.APP_PHONE is '부서신청자 전화번호';
//    COMMENT ON COLUMN APPLICATION.APP_EMAIL is '부서신청자 이메일';
//    COMMENT ON COLUMN APPLICATION.APP_BIRTH is '부서신청자 생년월일';
//    COMMENT ON COLUMN APPLICATION.APP_HIREDATE is '부서신청자 입사일';
//    COMMENT ON COLUMN APPLICATION.APP_LEVEL is '부서신청자 레벨 관리자임원사원';
//    COMMENT ON COLUMN APPLICATION.APP_STATUS is '부서신청자 재직여부(Y/N)';
//    COMMENT ON COLUMN APPLICATION.APP_DEPTNAME is '부서신청자 담당부서이름';
//    COMMENT ON COLUMN APPLICATION.APP_CHANGE is '부서신청자 희망부서이름';
//    COMMENT ON COLUMN APPLICATION.APP_REASON is '부서신청자 부서이동사유';
//    COMMENT ON COLUMN APPLICATION.APP_DATE IS '부서신청 작성일자';
//    COMMENT ON COLUMN APPLICATION.APP_ACCEPT is '부서신청 처리현황 승인거절검토중';
}
