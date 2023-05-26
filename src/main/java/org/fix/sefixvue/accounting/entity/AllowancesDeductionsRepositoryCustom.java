package org.fix.sefixvue.accounting.entity;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.fix.sefixvue.common.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.fix.sefixvue.accounting.entity.QAllowancesDeductions.allowancesDeductions;

@RequiredArgsConstructor
@Repository
public class AllowancesDeductionsRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Page<AllowancesDeductions> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition) {

        JPAQuery<AllowancesDeductions> query = queryFactory.selectFrom(allowancesDeductions).where(searchKeywords(searchCondition));

        long total = query.fetchCount();   //여기서 전체 카운트 후 아래에서 조건작업

        List<AllowancesDeductions> results = query
                .where(searchKeywords(searchCondition))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(allowancesDeductions.adno.asc())
                .fetch();

        return new PageImpl<>(results, pageable, total);
    }

    private BooleanExpression searchKeywords(SearchCondition searchCondition) {

    // 순번으로 검색
        if (searchCondition.getSk().equals("항목코드")) {
            int 항목코드 = Integer.parseInt(searchCondition.getSv());
            if (항목코드 != 0) {
                return allowancesDeductions.adcode.eq(항목코드);
            }

    // 이름으로 검색
        } else if (searchCondition.getSk().equals("항목이름")) {
            String 항목이름 = searchCondition.getSv();
            if (StringUtils.hasLength(항목이름)) {
                return allowancesDeductions.adname.contains(항목이름);
            }
        }

        return null;
    }
}