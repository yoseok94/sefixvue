package org.fix.sefixvue.board.entity;


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

import static org.fix.sefixvue.board.entity.QNotice.notice;


@RequiredArgsConstructor
@Repository
public class NoticeRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public Page<Notice> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition){
        JPAQuery<Notice> query = queryFactory.selectFrom(notice)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()));

        long total = query.stream().count();

        List<Notice> results = query
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(notice.noticeno.desc())
                .fetch();

        return new PageImpl<>(results, pageable, total);
    }

    private BooleanExpression searchKeywords(String sk, String sv) {
        if ("noticetitle".equals(sk)) {
            if (StringUtils.hasLength(sv)) {
                return notice.noticetitle.contains(sv);
            }
        }
        return null;
    }


}
