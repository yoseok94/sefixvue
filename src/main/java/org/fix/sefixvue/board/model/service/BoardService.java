package org.fix.sefixvue.board.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.admin.entity.DeptRepository;
import org.fix.sefixvue.admin.entity.Product;
import org.fix.sefixvue.admin.entity.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {
    private final DeptRepository deptRepository;
    private final ProductRepository productRepository;
}
