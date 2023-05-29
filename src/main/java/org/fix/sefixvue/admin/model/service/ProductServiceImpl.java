package org.fix.sefixvue.admin.model.service;

import lombok.RequiredArgsConstructor;
import org.fix.sefixvue.admin.entity.Product;
import org.fix.sefixvue.admin.entity.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public String getPicture(Long productno) throws Exception {
        Product product = repository.getOne(productno);
        return product.getProductimg();
    }
}
