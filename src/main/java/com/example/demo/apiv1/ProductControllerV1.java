package com.example.demo.apiv1;

import com.example.demo.entity.ProductEntity;
import com.example.demo.service.serviceimpl.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product")
public class ProductControllerV1 {
    private final ProductService productService;

    @PostMapping("")
    public CompletableFuture<String> addProduct(ProductEntity product) {
        for (int i = 1; i < 10; i++) {
            CompletableFuture.runAsync(() -> {
                String data = String.valueOf(new Date().getTime());
                product.setName(data);
                productService.save(product);
            });
        }
        return null;
    }

    @PostMapping("/many")
    public CompletableFuture<String> addProducts() {
        productService.saveMany(); // không có lỗi, vẫn lưu đủ theo mỗi trường đa luồng với các key liên tiếp
        return null;
    }

    @PostMapping("/save")
    public CompletableFuture<String> addProductss(ProductEntity product) throws InterruptedException {
        String data = String.valueOf(new Date().getTime());
        product.setName(data);
        productService.saveNew(product);
        return null;
    }

    @PostMapping("/saveNoT")
    public CompletableFuture<String> saveNoT(ProductEntity product) throws InterruptedException {
        String data = String.valueOf(new Date().getTime());
        product.setName(data);
        productService.saveNewNoT(product);
        return null;
    }

    @GetMapping("/updateProduct")
    public CompletableFuture<String> updateProduct(Long id) throws InterruptedException {
        productService.updateProduct(id);
        return null;
    }

    @GetMapping("/find")
    public ProductEntity findProductV1(Long id) throws InterruptedException {
        return productService.findById(id);
    }
}
