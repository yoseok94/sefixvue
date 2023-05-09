package org.fix.sefixvue.admin.entity;


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

import static org.fix.sefixvue.admin.entity.QDept.dept;

@RequiredArgsConstructor
@Repository
public class DeptRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Page<Dept> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition){
        JPAQuery<Dept> query = queryFactory.selectFrom(dept)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()));

        long total = query.stream().count();

        List<Dept> results = query
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()) //몇개의 데이터를 가져올지를 의미한다
                .orderBy(dept.deptno.desc())
                .fetch();

        return new PageImpl<>(results, pageable, total);
    }

    public BooleanExpression searchKeywords(String sk, String sv){
        if("deptid".equals(sk)){
            if(StringUtils.hasLength(sv)){
                return dept.deptid.contains(sv);
            }
        }else if("deptname".equals(sk)){
            if(StringUtils.hasLength(sv)){
                return dept.deptname.contains(sv);
            }
        }
        return null;
    }


}
