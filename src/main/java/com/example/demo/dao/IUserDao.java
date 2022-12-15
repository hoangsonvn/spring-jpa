package com.example.demo.dao;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;

import java.util.List;

public interface IUserDao {
    void persist(UserEntity userEntity);
    UserEntity save(UserEntity userEntity);
    UserEntity findById(Long id);
    List<UserEntity> findAll();
    void remove(Long id);
}
