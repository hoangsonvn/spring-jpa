package com.example.demo.service.serviceimpl;

import com.example.demo.dao.ICategoryDao;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryDao iCategoryDao;
    @Override
    public void persist(CategoryEntity category) {
        iCategoryDao.persist(category);
    }

    @Override
    public CategoryEntity findById(Long id) {
        return iCategoryDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        iCategoryDao.delete(id);
    }

    @Override
    public List<CategoryEntity> findAllRepo() {
        return iCategoryDao.findAllRepo();
    }

    @Override
    public CategoryEntity findByIdRepo(Long id) {
        return iCategoryDao.findByIdRepo(id);
    }

    @Override
    public List<CategoryEntity> findCategoryNativeById(Long id) {
        return iCategoryDao.findCategoryNativeById(id);
    }
}
