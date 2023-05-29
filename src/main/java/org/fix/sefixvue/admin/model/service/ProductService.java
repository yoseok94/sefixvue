package org.fix.sefixvue.admin.model.service;

import org.fix.sefixvue.admin.entity.Product;

public interface ProductService {
    public void regist(Product product) throws Exception;
    public String getPicture(Long productno) throws Exception;

}
