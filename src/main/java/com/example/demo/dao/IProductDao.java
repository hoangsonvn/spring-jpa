package com.example.demo.dao;

import com.example.demo.dto.Result;
import com.example.demo.entity.ProductEntity;

import java.util.List;

public interface IProductDao {
    void persist(ProductEntity productEntity);
    ProductEntity findByIdEF(Long id);
    ProductEntity save(ProductEntity productEntity);
    ProductEntity findById(Long id);
    List<ProductEntity> findAll();
    List<Object[]> findNameAndCode();
    List<Result> findNameAndCodeCustomClass(Long id);


}
