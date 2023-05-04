package org.fix.sefixvue.admin.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.admin.entity.DeptRepository;
import org.fix.sefixvue.admin.entity.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class AdminService{
    private final DeptRepository deptRepository;
    private final ProductRepository productRepository;
}
