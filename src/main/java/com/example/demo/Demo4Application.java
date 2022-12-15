package com.example.demo;

import com.example.demo.api.ProductController;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ICategoryRepository;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootApplication
public class Demo4Application implements CommandLineRunner {

    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private IProductService iProductService;

    public static void main(String[] args) {
        SpringApplication.run(Demo4Application.class, args);
    }

    public ResponseEntity findCategorym() {
        ProductEntity productEntity = iProductService.findByIdEF(1L);
        return ResponseEntity.ok(productEntity);
    }

    public ResponseEntity findAllRepo() {
        iCategoryService.findAllRepo();
        return ResponseEntity.ok(iCategoryService.findAllRepo());
    }

    public void findByIdRepo(Long id) {
        iCategoryService.findByIdRepo(id);
    }

    @Override
    public void run(String... args) throws Exception {
        //findByIdRepo(1L);
        // findAllRepo();
        //   findCategorym();
    }
}
