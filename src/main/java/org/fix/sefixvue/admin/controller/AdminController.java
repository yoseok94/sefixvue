package org.fix.sefixvue.admin.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.admin.entity.Dept;
import org.fix.sefixvue.admin.entity.Product;
import org.fix.sefixvue.admin.model.dto.DeptDto;
import org.fix.sefixvue.admin.model.dto.ProductDto;
import org.fix.sefixvue.admin.model.service.AdminService;
import org.fix.sefixvue.admin.model.service.ProductService;
import org.fix.sefixvue.common.Header;
import org.fix.sefixvue.common.SearchCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class AdminController {
    private final AdminService adminService;


    private final ProductService productService;

    @Value("${upload.path}")
    private String uploadPath;



//    @PostMapping("/product")
//    public Product productCreate(@RequestBody ProductDto productDto) {
//        return adminService.create(productDto);
//    }



    @PostMapping("/product")
    public ResponseEntity<Product> register(
            @RequestPart("product") String productString,
            @RequestPart("file") MultipartFile picture) throws Exception {

        log.info("productString: " + productString);

        Product product = new ObjectMapper().readValue(productString, Product.class);

        String productid = product.getProductid();
        String productname = product.getProductname();
        String productcategory = product.getProductcategory();
        int productcost = product.getProductcost();
        int purchaseprice = product.getPurchaseprice();
        int consumerprice = product.getConsumerprice();
//            Date productdate = product.getProductdate();
        String productremarks = product.getProductremarks();

        if(productid != null){
            log.info("product.getProductid(): " + productid);
            product.setProductid(productid);
        }
        if(productname != null){
            product.setProductname(productname);
        }
        if(productcategory != null){
            product.setProductcategory(productcategory);
        }
        if(productcost != 0){
            product.setProductcost(productcost);
        }
        if(purchaseprice != 0){
            product.setPurchaseprice(purchaseprice);
        }
        if(consumerprice != 0){
            product.setConsumerprice(consumerprice);
        }
        if(productremarks != null){
            product.setProductremarks(productremarks);
        }
        product.setPicture(picture);

        MultipartFile file = product.getPicture();

        log.info("originalName : " + file.getOriginalFilename());
        log.info("size : " + file.getSize());
        log.info("contentType : " + file.getContentType());

        String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());

        product.setProductimg(createdFileName);


//        this.adminService.regist(product);
        this.productService.regist(product);


        log.info("register product.getProductno : " + product.getProductno());

        Product createdProduct = new Product();
        createdProduct.setProductno(product.getProductno());

        return new ResponseEntity<>(createdProduct, HttpStatus.OK);
    }



    private String uploadFile(String originalName, byte[] fileData) throws Exception {
        UUID uid = UUID.randomUUID();

        String createdFileName = uid.toString() + "_" + originalName;

        File target = new File(uploadPath, createdFileName);

        FileCopyUtils.copy(fileData, target);

        return createdFileName;

    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> displayFile(Long productno) throws Exception {
        ResponseEntity<byte[]> entity = null;
        String fileName = productService.getPicture(productno);
        log.info("File name : " + fileName);
        try {
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            MediaType mediaType = getMediaType(formatName);
            HttpHeaders headers = new HttpHeaders();
            File file = new File(uploadPath + File.separator + fileName);
            if(mediaType != null){
                headers.setContentType(mediaType);
            }
            entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    private MediaType getMediaType(String formatName){
        if(formatName != null) {
            if(formatName.equals("JPG")) {
                return MediaType.IMAGE_JPEG;
            }

            if(formatName.equals("GIF")) {
                return MediaType.IMAGE_GIF;
            }

            if(formatName.equals("PNG")) {
                return MediaType.IMAGE_PNG;
            }
        }

        return null;
    }


//    @GetMapping("/product/{productno}")
//    public ProductDto getProduct(@PathVariable Long productno) {
//        return adminService.getProduct(productno);
//    }
//

    @GetMapping("/product/{productno}")
    public ResponseEntity<Product> read(@PathVariable("productno") Long productno) throws Exception {
        log.info("read");
        Product product = this.productService.read(productno);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }




    @PutMapping
    public ResponseEntity<Product> modify(@RequestPart("product") String productString, @RequestPart(name = "file", required = false) MultipartFile picture) throws Exception {

        log.info("itemString: " + productString);

        Product product = new ObjectMapper().readValue(productString, Product.class);

        String productid = product.getProductid();
        String productname = product.getProductname();
        String productcategory = product.getProductcategory();
        int productcost = product.getProductcost();
        int purchaseprice = product.getPurchaseprice();
        int consumerprice = product.getConsumerprice();
        String productremarks = product.getProductremarks();
        if(productid != null){
            log.info("product.getProductid(): " + productid);
            product.setProductid(productid);
        }
        if(productname != null){
            product.setProductname(productname);
        }
        if(productcategory != null){
            product.setProductcategory(productcategory);
        }
        if(productcost != 0){
            product.setProductcost(productcost);
        }
        if(purchaseprice != 0){
            product.setPurchaseprice(purchaseprice);
        }
        if(consumerprice != 0){
            product.setConsumerprice(consumerprice);
        }
        if(productremarks != null){
            product.setProductremarks(productremarks);
        }
        if(picture != null) {
            product.setPicture(picture);
            MultipartFile file = product.getPicture();

            log.info("originalName: " + file.getOriginalFilename());
            log.info("size: " + file.getSize());
            log.info("contentType: " + file.getContentType());

            String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
            product.setProductimg(createdFileName);
        }
        else {
            Product oldProduct = this.productService.read(product.getProductno());
            product.setProductimg(oldProduct.getProductimg());
        }
        this.productService.modify(product);
        Product modifiedItem = new Product();
        modifiedItem.setProductno(product.getProductno());
        return new ResponseEntity<>(modifiedItem, HttpStatus.OK);
    }







    //-----------------------------
    //View lists by page
    @GetMapping("/dept/list")
    public Header<List<DeptDto>> deptList(
            @PageableDefault(sort = {"deptno"}) Pageable pageable,
            SearchCondition searchCondition) {
        return adminService.getDeptList(pageable, searchCondition);
    }



    //read(get)
    @GetMapping("/dept/{deptno}")
    public DeptDto getDept(@PathVariable Long deptno) {
        return adminService.getDept(deptno);
    }


    //regist(insert)
    @PostMapping("/dept")
    public Dept deptWrite(@RequestBody DeptDto deptDto) {

        return adminService.create(deptDto);
    }

    //modify(update)
    @PatchMapping("/dept")
    public Dept deptUp(@RequestBody DeptDto deptDto){
        return  adminService.update(deptDto);
    }


//    @PatchMapping("/dept/updept")
//    public Dept deptUp(@RequestBody DeptDto deptDto){
//        return  adminService.update(deptDto);
//    }


//@PatchMapping("/dept/{deptno}")
//public Dept deptUp(@PathVariable Long deptno, @RequestBody DeptDto deptDto) {
//    deptDto.setDeptno(deptno); // 'id' 파라미터를 'deptDto' 객체에 설정
//    return adminService.update(deptDto);
//}

    @DeleteMapping("/dept/{deptno}")
    public void delDept(@PathVariable Long deptno) {
        adminService.delete(deptno);
    }



    //------------------------------------------------------------------------
    @GetMapping("/product/list")
    public Header<List<ProductDto>> productList(
            @PageableDefault(sort = {"productno"}) Pageable pageable,
            SearchCondition searchCondition) {
        return adminService.getProductList(pageable, searchCondition);
    }

//    @GetMapping("/product/{productno}")
//    public ProductDto getProduct(@PathVariable Long productno) {
//        return adminService.getProduct(productno);
//    }
//



//    @PostMapping("/product")
//    public Product productCreate(@RequestBody ProductDto productDto) {
//        return adminService.create(productDto);
//    }








//
//    @PatchMapping("/product")
//    public Product productUpdate(@RequestBody ProductDto productDto){
//        return adminService.update(productDto);
//    }
//
    @DeleteMapping("/product/{productno}")
    public void productDelete(@PathVariable Long productno){
        adminService.delete(productno);
    }

}