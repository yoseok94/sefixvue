package org.fix.sefixvue.management.entity;

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

import static org.fix.sefixvue.management.entity.QApplication.application;

@RequiredArgsConstructor
@Repository
public class ApplicationRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public Page<Application> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition) {
        JPAQuery<Application> query = queryFactory.selectFrom(application)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()));

        long total = query.stream().count();   //여기서 전체 카운트 후 아래에서 조건작업

        List<Application> results = query
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(application.appno.desc())
                .fetch();

        return new PageImpl<>(results, pageable, total);
    }

    private BooleanExpression searchKeywords(String sk, String sv) {
        if("ID".equals(sk)) {
            if(StringUtils.hasLength(sv)) {
                return application.appid.contains(sv);
            }
        } else if ("Name".equals(sk)) {
            if(StringUtils.hasLength(sv)) {
                return application.appname.contains(sv);
            }
        } else if ("Deptname".equals(sk)) {
            if(StringUtils.hasLength(sv)) {
                return application.appdeptname.contains(sv);
            }
        } else if ("Change".equals(sk)) {
            if(StringUtils.hasLength(sv)) {
                return application.appchange.contains(sv);
            }
        }else if ("Level".equals(sk)) {
            if (StringUtils.hasLength(sv)) {
                return application.applevel.contains(sv);
            }
        }else if ("Accept".equals(sk)) {
            if (StringUtils.hasLength(sv)) {
                return application.appaccept.contains(sv);
            }
        }

        return null;
    }
}