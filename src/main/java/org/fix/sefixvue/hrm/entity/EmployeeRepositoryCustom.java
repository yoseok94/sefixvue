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

import java.util.List;

import static org.fix.sefixvue.hrm.entity.QEmployee.employee;

@RequiredArgsConstructor
@Repository
public class EmployeeRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public Page<Employee> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition) {
        JPAQuery<Employee> query = queryFactory.selectFrom(employee)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()));

        long total = query.stream().count();   //여기서 전체 카운트 후 아래에서 조건작업

        List<Employee> results = query
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(employee.empno.desc())
                .fetch();

        return new PageImpl<>(results, pageable, total);
    }

    private BooleanExpression searchKeywords(String sk, String sv) {
        if("ID".equals(sk)) {
            if(StringUtils.hasLength(sv)) {
                return employee.empid.contains(sv);
            }
        } else if ("Name".equals(sk)) {
            if(StringUtils.hasLength(sv)) {
                return employee.empname.contains(sv);
            }
        }

        return null;
    }
}
