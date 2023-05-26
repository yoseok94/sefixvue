package org.fix.sefixvue.accounting.entity;

import com.querydsl.core.types.Predicate;
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

import java.sql.Date;
import java.util.List;

import static org.fix.sefixvue.accounting.entity.QSalary.salary;
import static org.fix.sefixvue.accounting.entity.QSlipstatement.slipstatement;
import static org.fix.sefixvue.business.entity.QTrade.trade;

@RequiredArgsConstructor
@Repository
public class SlipstatementRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Page<Slipstatement> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition) {

        JPAQuery<Slipstatement> query = queryFactory.selectFrom(slipstatement)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()));

        long total = query.fetchCount();

        List<Slipstatement> results = query
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(slipstatement.slipstatementno.desc())
                .fetch();


        return new PageImpl<>(results, pageable, total);
    }

    public BooleanExpression searchKeywords(String sk, String sv) {
        if ("slipstatementno".equals(sk)) {
            if (StringUtils.hasLength(sv)) {
                return slipstatement.slipstatementno.eq(Long.parseLong(sv));
            }
        } else if ("tradetype".equals(sk)) {
            if (StringUtils.hasLength(sv)) {
                return slipstatement.tradetype.contains(sv);
            }
        }
        return null;
    }
}