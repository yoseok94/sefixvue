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
    private long dept_no;    // 부서 순번
    private String product_id;    // 상품 아이디
    private String dept_id;    // 부서 아이디
    private String dept_name;    // 부서 이름
    private java.sql.Date dept_date;    // 부서 작성일시
}
