package org.fix.sefixvue.accounting.entity;

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

import static org.fix.sefixvue.accounting.entity.QSlipstatement.slipstatement;
import static org.fix.sefixvue.business.entity.QTrade.trade;

@RequiredArgsConstructor
@Repository
public class SlipstatementRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Page<Slipstatement> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition) {

        JPAQuery<Slipstatement> query = queryFactory.selectFrom(slipstatement).where(searchKeywords(searchCondition));

        long total = query.fetchCount();   //여기서 전체 카운트 후 아래에서 조건작업

        List<Slipstatement> results = query
                .where(searchKeywords(searchCondition))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(slipstatement.slipstatementno.desc())
                .fetch();

        return new PageImpl<>(results, pageable, total);
    }

    private BooleanExpression searchKeywords(SearchCondition searchCondition) {
        // 전표 번호로 조회
        if (searchCondition.getSk().equals("slipstatementdate")) {
            Date slipstatementdate = Date.valueOf(searchCondition.getSv());
            if (slipstatementdate != null) {
                return slipstatement.slipstatementdate.eq(slipstatementdate);
            }

        // 거래처 명으로 조회
        } else if (searchCondition.getSk().equals("accountname")) {
            String accountname = searchCondition.getSv();
            if (StringUtils.hasLength(accountname)) {
                return trade.accountname.contains(accountname);
            }
        }

        return null;
    }
}