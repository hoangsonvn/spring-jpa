package com.example.demo.service;

import com.example.demo.dto.Result;
import com.example.demo.entity.ProductEntity;

import java.util.List;

public interface IProductService {
    void persist(ProductEntity productEntity);
    ProductEntity findByIdEF(Long id);
    ProductEntity save(ProductEntity productEntity);
    ProductEntity findById(Long id) throws InterruptedException;
    List<ProductEntity> findAll();
    List<Object[]> findNameAndCode();
    List<Result> findNameAndCodeCustomClass(Long id);
}
