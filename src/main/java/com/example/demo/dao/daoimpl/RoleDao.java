package com.example.demo.dao.daoimpl;

import com.example.demo.dao.IRoleDao;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.RoleEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDao implements IRoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void persist(RoleEntity roleEntity) {
        entityManager.persist(roleEntity);
    }

    @Transactional
    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        return entityManager.merge(roleEntity);
    }

    @Override
    public RoleEntity findById(Long id) {
        return entityManager.find(RoleEntity.class, id);
    }

    @Override
    public List<RoleEntity> findAll() {
        String sql = "select r from RoleEntity r";
        return entityManager.createQuery(sql, RoleEntity.class).getResultList();
    }

    @Override
    public void remove(Long id) {
        entityManager.remove(this.entityManager.find(RoleEntity.class,id));
    }
}
