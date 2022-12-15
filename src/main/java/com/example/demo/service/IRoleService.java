package com.example.demo.service;

import com.example.demo.entity.RoleEntity;

import java.util.List;

public interface IRoleService {
    void persist(RoleEntity roleEntity);
    RoleEntity save(RoleEntity roleEntity);
    RoleEntity findById(Long id);
    List<RoleEntity> findAll();
    void remove(Long id);
}
