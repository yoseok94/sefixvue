package org.fix.sefixvue.admin.entity;


import org.springframework.util.StringUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.fix.sefixvue.common.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


import static org.fix.sefixvue.admin.entity.QProduct.product;


@RequiredArgsConstructor
@Repository
public class ProductRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public Page<Product> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition){
        JPAQuery<Product> query = queryFactory.selectFrom(product)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()));

        long total = query.stream().count();

        List<Product> results = query
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()) //몇개의 데이터를 가져올지를 의미한다
                .orderBy(product.productno.desc())
                .fetch();

        return  new PageImpl<>(results, pageable, total);
    }

    public BooleanExpression searchKeywords(String sk, String sv){
        if("productid".equals(sk)){
            if (StringUtils.hasLength(sv)){
                return product.productid.contains(sv);
            }
        }else if("productname".equals(sk)){
            if(StringUtils.hasLength(sv)){
                return product.productname.contains(sv);
            }
        }else if("productcategory".equals(sk)){
            if(StringUtils.hasLength(sv)){
                return product.productcategory.contains(sv);
            }
        }
        return null;


    }

}
