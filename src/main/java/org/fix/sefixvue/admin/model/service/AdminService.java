package org.fix.sefixvue.admin.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.admin.entity.*;

import org.fix.sefixvue.admin.model.dto.DeptDto;
import org.fix.sefixvue.admin.model.dto.ProductDto;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.Pagination;
import org.fix.sefixvue.common.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class AdminService{
    private final DeptRepository deptRepository;
    private final DeptRepositoryCustom deptRepositoryCustom;
    private final ProductRepository productRepository;
    private final ProductRepositoryCustom productRepositoryCustom;

    //dept list, paging, search
    public Header<List<DeptDto>> getDeptList(Pageable pageable, SearchCondition searchCondition) {
        List<DeptDto> dtos = new ArrayList<>();
        Page<Dept> deptEntities = deptRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        for (Dept entity : deptEntities) {
            DeptDto ddto = DeptDto.builder()
                    .deptno(entity.getDeptno())
                    .productid(entity.getProductid())
                    .deptid(entity.getDeptid())
                    .deptname(entity.getDeptname())
                    .deptdate(entity.getDeptdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
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

    //one search dept
    public DeptDto getDept(Long deptno) {
        Dept entity = deptRepository.findById(deptno).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        // findById(id) 하면 SELECT + WHERE 작동됨. 람다 표현식 사용됨. 의미는 ~~ 조건을 만족시켰을 떄 에러 발생시켜라
        return DeptDto.builder()
                .deptno(entity.getDeptno())
                .productid(entity.getProductid())
                .deptid(entity.getDeptid())
                .deptname(entity.getDeptname())
                .deptdate(entity.getDeptdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .build();
        // 컬럼값 하나씩 꺼내서 매핑 -> DTO 객체 리턴
    }



    //insert dept
    public Dept create(DeptDto deptDto){
        Dept entity = Dept.builder()
                .deptid(deptDto.getDeptid())
                .productid(deptDto.getProductid())
                .deptname(deptDto.getDeptname())
                .deptdate(LocalDateTime.now())
                .build();
        return deptRepository.save(entity);
    }

    //update dept
    public Dept update(DeptDto deptDto){
        Dept entity = deptRepository.findById(deptDto.getDeptno()).orElseThrow(() -> new RuntimeException("못찾음"));
        entity.setDeptid(deptDto.getDeptid());
        entity.setDeptname(deptDto.getDeptname());
        entity.setProductid(deptDto.getProductid());
        return deptRepository.save(entity);
    }


    //delete dept
    public void deleteDep(Long deptno){
        Dept entity = deptRepository.findById(deptno).orElseThrow(() -> new RuntimeException("없음"));
        deptRepository.delete(entity);
    }

    //----------------------------------------------------------------------


    //product list, paging, search
    public Header<List<ProductDto>> getProductList(Pageable pageable, SearchCondition searchCondition){
        List<ProductDto> ptos = new ArrayList<>();

        Page<Product> productEntities = productRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        for(Product pentity : productEntities){
            ProductDto ppto = ProductDto.builder()
                    .productno(pentity.getProductno())
                    .productid(pentity.getProductid())
                    .productname(pentity.getProductname())
                    .productcategory(pentity.getProductcategory())
                    .productcost(pentity.getProductcost())
                    .purchaseprice(pentity.getPurchaseprice())
                    .consumerprice(pentity.getConsumerprice())
                    .productdate(pentity.getProductdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .productremarks(pentity.getProductremarks())
                    .build();
            ptos.add(ppto);
        }
        Pagination pagination = new Pagination(
                (int) productEntities.getTotalElements()
                ,pageable.getPageNumber() + 1
                , pageable.getPageSize()
                ,10
        );

        return Header.OK(ptos, pagination);
    }


    public ProductDto getProduct(Long productno) {
        Product entity = productRepository.findById(productno).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        // findById(id) 하면 SELECT + WHERE 작동됨. 람다 표현식 사용됨. 의미는 ~~ 조건을 만족시켰을 떄 에러 발생시켜라
        return ProductDto.builder()
                .productno(entity.getProductno())
                .productid(entity.getProductid())
                .productname(entity.getProductname())
                .productcategory(entity.getProductcategory())
                .productcost(entity.getProductcost())
                .purchaseprice(entity.getPurchaseprice())
                .consumerprice(entity.getConsumerprice())
                .productdate(entity.getProductdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .productremarks(entity.getProductremarks())
                .productimg(entity.getProductimg())
                .build();
    }


    //isnert product
    public Product create(ProductDto productDto){
        Product pentity = Product.builder()
                .productid(productDto.getProductid())
                .productname(productDto.getProductname())
                .productcategory(productDto.getProductcategory())
                .productcost(productDto.getProductcost())
                .purchaseprice(productDto.getPurchaseprice())
                .consumerprice(productDto.getConsumerprice())
                .productdate(LocalDateTime.now())
                .productremarks(productDto.getProductremarks())
                .build();
        return productRepository.save(pentity);
    }

    //update product
    public Product update(ProductDto productDto){
        Product entity = productRepository.findById(productDto.getProductno()).orElseThrow(() -> new RuntimeException("못찾음"));
        entity.setProductid(productDto.getProductid());
        entity.setProductname(productDto.getProductname());
        entity.setProductcategory(productDto.getProductcategory());
        entity.setProductcost(productDto.getProductcost());
        entity.setPurchaseprice(productDto.getPurchaseprice());
        entity.setConsumerprice(productDto.getConsumerprice());
        entity.setProductremarks(productDto.getProductremarks());
        return productRepository.save(entity);
    }

    //delete product
    public void deletePro(Long productno){
        Product entity = productRepository.findById(productno).orElseThrow(() -> new RuntimeException("없음"));
        productRepository.delete(entity);
    }


//    public ProductDto getProduct(Long productno){
//        Product entity = productRepository.findById(productno).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
//
//        return ProductDto.builder()
//                .productno(entity.getProductno())
//                .productid(entity.getProductid())
//                .productname(entity.getProductname())
//                .productcategory(entity.getProductcategory())
//                .productcost(entity.getProductcost())
//                .purchaseprice(entity.getPurchaseprice())
//                .consumerprice(entity.getConsumerprice())
//                .productdate(entity.getProductdate())
//                .productimg(entity.getProductimg())
//                .productremarks(entity.getProductremarks())
//                .build();
//
//    }



}
