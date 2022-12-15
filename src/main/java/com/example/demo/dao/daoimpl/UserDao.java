package com.example.demo.dao.daoimpl;

import com.example.demo.dao.IUserDao;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao implements IUserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void persist(UserEntity userEntity) {
        entityManager.persist(userEntity);
    }

    @Transactional
    @Override
    public UserEntity save(UserEntity userEntity) {
        return entityManager.merge(userEntity);
    }

    @Override
    public UserEntity findById(Long id) {
        return entityManager.find(UserEntity.class, id);
    }

    @Override
    public List<UserEntity> findAll() {
        String sql = "Select u from UserEntity u";
        return entityManager.createQuery(sql, UserEntity.class).getResultList();
    }
@Transactional
    @Override
    public void remove(Long id) {
        entityManager.remove(this.entityManager.find(UserEntity.class,id));// se xoa ca cot phu cho du khong add role vi có lệnh find tìm ra rồi
    }
}
