package org.fix.sefixvue.admin.model.service;

import org.fix.sefixvue.admin.entity.Product;

import java.util.List;

public interface ProductService {


    public void regist(Product product) throws Exception;
    public Product read(Long productno) throws Exception;
    public void modify(Product product) throws Exception;

    public String getPicture(Long productno) throws Exception;


}
