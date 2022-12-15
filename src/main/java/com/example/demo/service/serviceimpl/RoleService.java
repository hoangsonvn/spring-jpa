package com.example.demo.service.serviceimpl;

import com.example.demo.dao.IRoleDao;
import com.example.demo.dao.IUserDao;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleDao iRoleDao;
    @Autowired
    private IUserDao iUserDao;

    @Transactional
    @Override
    public void persist(RoleEntity roleEntity) {
        UserEntity user = new UserEntity();
        UserEntity use1 = new UserEntity();
        user.setUsername("da");
        use1.setUsername("as");
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(use1);
        userEntities.add(user); //nếu không dùng casade thì user không id nó cũng k vào
        roleEntity.setUsers(userEntities);
        iRoleDao.persist(roleEntity);
    }

    @Transactional
    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        UserEntity user = iUserDao.findById(1L);
        UserEntity use1 = iUserDao.findById(2L);
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(use1);
        userEntities.add(user);
        roleEntity.setUsers(userEntities);
        return iRoleDao.save(roleEntity);
    }

    @Override
    public RoleEntity findById(Long id) {
        return iRoleDao.findById(id);
    }

    @Override
    public List<RoleEntity> findAll() {
        return iRoleDao.findAll();
    }

    @Override
    public void remove(Long id) {
        iRoleDao.remove(id);
    }
}
