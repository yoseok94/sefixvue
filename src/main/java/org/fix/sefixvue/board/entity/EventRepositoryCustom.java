package org.fix.sefixvue.board.entity;

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

import static org.fix.sefixvue.board.entity.QEvent.event;


@RequiredArgsConstructor
@Repository
public class EventRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public Page<Event> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition){
        JPAQuery<Event> query = queryFactory.selectFrom(event)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()));

        long total = query.stream().count();

        List<Event> results = query
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(event.eventno.desc())
                .fetch();

        return new PageImpl<>(results, pageable, total);
    }

    private BooleanExpression searchKeywords(String sk, String sv) {
        if ("eventtitle".equals(sk)) {
            if (StringUtils.hasLength(sv)) {
                return event.eventtitle.contains(sv);
            }
        }
        return null;
    }


}
