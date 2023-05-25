package org.fix.sefixvue.hrm.entity;

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

import java.time.LocalDate;
import java.util.List;

import static org.fix.sefixvue.hrm.entity.QAttendence.attendence;

@RequiredArgsConstructor
@Repository
public class AttendenceRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    public Page<Attendence> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition) {
        JPAQuery<Attendence> query = queryFactory.selectFrom(attendence)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()));

        long total = query.stream().count();   //여기서 전체 카운트 후 아래에서 조건작업

        List<Attendence> results = query
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(attendence.attendenceno.desc())
                .fetch();

        return new PageImpl<>(results, pageable, total);
    }

    private BooleanExpression searchKeywords(String sk, String sv) {
        if("ID".equals(sk)) {
            if(StringUtils.hasLength(sv)) {
                return attendence.empId.contains(sv);
            }
        } else if ("Name".equals(sk)) {
            if(StringUtils.hasLength(sv)) {
                return attendence.empname.contains(sv);
            }
        }

        return null;
    }

}
