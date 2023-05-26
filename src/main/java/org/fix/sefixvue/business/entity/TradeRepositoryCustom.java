package org.fix.sefixvue.business.entity;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.fix.sefixvue.common.SearchCondition;
import org.fix.sefixvue.hrm.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

import static org.fix.sefixvue.business.entity.QTrade.trade;


@RequiredArgsConstructor
@Repository
public class TradeRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    public Page<Trade> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition) {
        JPAQuery<Trade> query = queryFactory.selectFrom(trade)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()));

        long total = query.stream().count();   //여기서 전체 카운트 후 아래에서 조건작업

        List<Trade> results = query
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(trade.tradingno.desc())
                .fetch();

        return new PageImpl<>(results, pageable, total);
    }

    private BooleanExpression searchKeywords(String sk, String sv) {
        if ("거래처이름".equals(sk)) {
            if (StringUtils.hasLength(sv)) {
                return trade.accountname.contains(sv);
            }
        } else if ("상품이름".equals(sk)) {
            if (StringUtils.hasLength(sv)) {
                return trade.productname.contains(sv);
            }
        }

        return null;
    }
}