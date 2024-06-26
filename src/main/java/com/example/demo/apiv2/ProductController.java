package com.example.demo.apiv2;

import com.example.demo.dto.Result;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ICategoryRepository;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private ICategoryRepository iCategoryRepository;
//    @PostMapping("/product")
//    @ResponseBody
//    public ResponseEntity persist(@RequestBody ProductEntity productEntity) {
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setId(1L);
//        productEntity.setCategory(categoryEntity);
//        iProductService.persist(productEntity);
//        return ResponseEntity.ok(productEntity);
//    }

//    @GetMapping("/product/{id}")
//    @ResponseBody
//    public ResponseEntity findBdemo4yIdEF(@PathVariable(value = "id") long id) {
//        ProductEntity productEntity = iProductService.findByIdEF(id);
//        productEntity.getCategory().getName();
//        return ResponseEntity.ok(productEntity);
//    }

    @GetMapping("/product")
    public String findAll() {

//        List<ProductEntity> productEntities = iProductService.findAll();
//        for (ProductEntity p : productEntities) {
//            p.setCategory(iCategoryService.findById(p.getCategory().getId()));
//        }
        return "nasdsad";
    }


    @GetMapping("/product/nameandcode")
    @ResponseBody
    public ResponseEntity<List<Object[]>> findNameAndCode() {
        List<Object[]> objs = iProductService.findNameAndCode();
        return ResponseEntity.ok(objs);
    }

    @GetMapping("/product/nameandcodecustomerclass/{id}")
    @ResponseBody
    public ResponseEntity findNameAndCodeCustomerClass(@PathVariable(value = "id") Long id) {
        List<Result> objs = iProductService.findNameAndCodeCustomClass(id);
        return ResponseEntity.ok(objs);
    }


    @PostMapping("/repository/product")
    @ResponseBody
    public ResponseEntity persistRepo() {
        iProductRepository.deleteAll();
        return ResponseEntity.ok("done");
    }

    @GetMapping("/repository/product/{id}")
    @ResponseBody
    public ResponseEntity findByIdRepo(@PathVariable(value = "id") long id) throws InterruptedException {
        iProductService.findById(6l);
        ResponseEntity.ok(iProductService.findById(id));
        return null;
    }

    @GetMapping("/product/{id}")
    @ResponseBody
    public ResponseEntity findProduct(@PathVariable(value = "id") long id) {
        ProductEntity productEntity = iProductRepository.findById(id).orElse(new ProductEntity());
        return ResponseEntity.ok(productEntity);
    }

    @PostMapping("/product")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody ProductEntity productEntity) {
        CategoryEntity categoryEntity = iCategoryRepository.findById(2L).orElse(new CategoryEntity());
        productEntity.setCategory(categoryEntity);
        iProductService.save(productEntity);
        System.out.println(123);
        return ResponseEntity.ok("ok".toString());
    }

    @DeleteMapping("/product")
    @ResponseBody
    public ResponseEntity<?> dewletasd(@RequestParam Long id) {
        ProductEntity product = iProductRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        iProductRepository.delete(product);
        return ResponseEntity.ok("done");
    }
}
