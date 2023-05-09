package org.fix.sefixvue.admin.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.admin.entity.Dept;
import org.fix.sefixvue.admin.entity.DeptRepository;
import org.fix.sefixvue.admin.entity.DeptRepositoryCustom;

import org.fix.sefixvue.admin.model.dto.DeptDto;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.Pagination;
import org.fix.sefixvue.common.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class AdminService{
    private final DeptRepository deptRepository;
    private final DeptRepositoryCustom deptRepositoryCustom;

    public Header<List<DeptDto>> getDeptList(Pageable pageable, SearchCondition searchCondition) {
        List<DeptDto> dtos = new ArrayList<>();

        Page<Dept> deptEntities = deptRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        for (Dept entity : deptEntities) {
            DeptDto ddto = DeptDto.builder()
                    .deptno(entity.getDeptno())
                    .productid(entity.getProductid())
                    .deptid(entity.getDeptid())
                    .deptname(entity.getDeptname())
                    .deptdate(entity.getDeptdate())
                    .build();
            dtos.add(ddto);

        }
        Pagination pagination = new Pagination(
                (int) deptEntities.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 9
        );
        return Header.OK(dtos, pagination);
    }


    public Dept create(DeptDto deptDto){
        Dept entity = Dept.builder()
                .deptid(deptDto.getDeptid())
                .deptname(deptDto.getDeptname())
                .productid(deptDto.getProductid())
                .build();
        return deptRepository.save(entity);
    }

    public Dept update(DeptDto deptDto){
        Dept entity = deptRepository.findById(deptDto.getDeptno()).orElseThrow(() -> new RuntimeException("못찾음"));
        entity.setDeptid(deptDto.getDeptid());
        entity.setDeptname(deptDto.getDeptname());
        entity.setProductid(deptDto.getProductid());

        return deptRepository.save(entity);
    }

    public void delete(Long id){
        Dept entity = deptRepository.findById(id).orElseThrow(() -> new RuntimeException("없음"));
        deptRepository.delete(entity);
    }



}
