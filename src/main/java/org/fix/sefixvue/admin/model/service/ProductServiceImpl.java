package org.fix.sefixvue.admin.model.service;


import lombok.RequiredArgsConstructor;
import org.fix.sefixvue.admin.entity.Product;
import org.fix.sefixvue.admin.entity.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{


        private final ProductRepository repository;


    @Override
    @Transactional
    public void regist(Product product) throws Exception {
        repository.save(product);

    }

    @Override
    @Transactional(readOnly = true)
    public Product read(Long productno) throws Exception {
        return repository.getOne(productno);
    }

    @Override
    @Transactional
    public void modify(Product product) throws Exception {
        Product productEntity = repository.getOne(product.getProductno());

        productEntity.setProductid(product.getProductid());
        productEntity.setProductname(product.getProductname());
        productEntity.setProductcategory(product.getProductcategory());
        productEntity.setProductcost(product.getProductcost());
        productEntity.setPurchaseprice(product.getPurchaseprice());
        productEntity.setConsumerprice(product.getConsumerprice());
        productEntity.setProductremarks(product.getProductremarks());
        productEntity.setProductimg(product.getProductimg());



    }

    @Override
    public String getPicture(Long productno) throws Exception {
        Product product = repository.getOne(productno);
        return product.getProductimg();
    }
}
