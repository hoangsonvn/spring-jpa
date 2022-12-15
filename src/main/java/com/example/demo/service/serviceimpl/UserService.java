package com.example.demo.service.serviceimpl;

import com.example.demo.dao.IRoleDao;
import com.example.demo.dao.daoimpl.RoleDao;
import com.example.demo.dao.daoimpl.UserDao;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private IRoleDao iRoleDao;

    @Override
    public void persist(UserEntity userEntity) {
        userDao.persist(userEntity);
    }
    @Override
    public UserEntity save(UserEntity userEntity) {
        RoleEntity role = new RoleEntity();
        role.setId(3L);// sử dụng như này thằng hibernate vẫn chèn vào cột phụ nhé, nếu chưa có thì nó tự động insert
        role.setRoleName("hah1a");// nhưng nếu không dùng casade thì idUser=10 giả sử chưa có trong Db  thi chạy PUT cũng k có
        RoleEntity role2 = iRoleDao.findById(2L);// và nếu idols 1 cái có ,1 cái dạng new mà có thì cả 2 nó đều có trong data bảng phụ
        role2.setRoleName("k");
        List<RoleEntity> roleServices = new ArrayList<>();
        roleServices.add(role);
        roleServices.add(role2);
        userEntity.setRoles(roleServices);
        UserEntity user= userDao.save(userEntity);
         user.setUsername("al1l1a");
        return user;
    }

    @Override
    public UserEntity findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userDao.findAll();
    }

    @Override
    public void remove(Long id) {
        userDao.remove(id);
    }
}
