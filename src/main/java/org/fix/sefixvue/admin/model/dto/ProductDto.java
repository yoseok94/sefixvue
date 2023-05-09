package org.fix.sefixvue.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private long productno;    // 상품 순번
    private String productid;    // 상품 아이디
    private String productname;    // 상품 이름
    private String productcategory;    // 상품 카테고리
    private int productcost;    // 상품 원가
    private int purchaseprice;    // 상품 구매가
    private int consumerprice;    // 상품 소비자가
    private java.sql.Date productdate;    // 상품 작성 일시
    private String productimg;    // 상품 첨부 이미지
    private String productremarks;    // 상품 비고

}
