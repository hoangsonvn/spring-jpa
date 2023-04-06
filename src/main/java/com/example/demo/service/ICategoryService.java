package com.example.demo.service;

import com.example.demo.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    void persist(CategoryEntity category);

    CategoryEntity findById(Long id);

    void delete(Long id);

    List<CategoryEntity> findAllRepo();

    CategoryEntity findByIdRepo(Long id);

    List<CategoryEntity> findCategoryNativeById(Long id);

    void saveManyProducts(long id);
}
