package com.example.demo.dao;

import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.RoleEntity;

import java.util.List;

public interface IRoleDao {
    void persist(RoleEntity roleEntity);
    RoleEntity save(RoleEntity roleEntity);
    RoleEntity findById(Long id);
    List<RoleEntity> findAll();
    void remove(Long id);
}
