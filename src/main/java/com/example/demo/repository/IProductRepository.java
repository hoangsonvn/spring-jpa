package com.example.demo.repository;

import com.example.demo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductRepository extends JpaRepository<ProductEntity,Long> {
    @Query("select p from ProductEntity p where p.title= :title")
    ProductEntity findByCode(String title);

}
