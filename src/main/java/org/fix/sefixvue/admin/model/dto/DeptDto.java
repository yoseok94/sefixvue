package org.fix.sefixvue.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeptDto {
    private long deptno;    // 부서 순번
    private String productid;    // 상품 아이디
    private String deptid;    // 부서 아이디
    private String deptname;    // 부서 이름
    private String deptdate;    // 부서 작성일시
}
