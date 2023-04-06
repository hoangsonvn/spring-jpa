package com.example.demo.service.serviceimpl;

import com.example.demo.dao.ICategoryDao;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ICategoryRepository;
import com.example.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository iCategoryRepository;
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

    @Override
    @Transactional
    public void saveManyProducts(long id) {
try{
        CategoryEntity categoryEntity = iCategoryRepository.findById(id).orElse(new CategoryEntity());

        int random = (int)(Math.random() * 50 + 1);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("van" + random);
        productEntity.setTitle(String.valueOf(random));

        categoryEntity.addProduct(productEntity);
        iCategoryRepository.save(categoryEntity);
        int random1 = (int)(Math.random() * 50 + 1);

        ProductEntity productEntit1 = new ProductEntity();
        productEntit1.setName("van" + random1);
        productEntit1.setTitle(String.valueOf(random1));
        categoryEntity.addProduct(productEntit1);
        iCategoryRepository.save(categoryEntity);
        throw new Exception("Asd");}catch (Exception e){
    System.out.println("123");
        }
    }


}
