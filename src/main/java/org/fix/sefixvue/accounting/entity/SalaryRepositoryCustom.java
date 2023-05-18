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

import static org.fix.sefixvue.accounting.entity.QSalary.salary;

@RequiredArgsConstructor
@Repository
public class SalaryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Page<Salary> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition) {

        JPAQuery<Salary> query = queryFactory.selectFrom(salary)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()));

        long total = query.stream().count();

        List<Salary> results = query
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(salary.salaryno.desc())
                .fetch();


        return new PageImpl<>(results, pageable, total);
    }

    public BooleanExpression searchKeywords(String sk, String sv) {
        if ("empid".equals(sk)) {
            if (StringUtils.hasLength(sv)) {
                return salary.empid.contains(sv);
            }
        } else if ("empname".equals(sk)) {
            if (StringUtils.hasLength(sv)) {
                return salary.empname.contains(sv);
            }
        }
        return null;
    }
}
