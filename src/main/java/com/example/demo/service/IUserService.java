package com.example.demo.service;

import com.example.demo.entity.UserEntity;

import java.util.List;

public interface IUserService {
    void persist(UserEntity userEntity);
    UserEntity save(UserEntity userEntity);
    UserEntity findById(Long id);
    List<UserEntity> findAll();
    void remove(Long id);
}
