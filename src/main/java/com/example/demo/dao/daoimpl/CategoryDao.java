package com.example.demo.dao.daoimpl;

import com.example.demo.dao.ICategoryDao;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ICategoryRepository;
import com.example.demo.repository.IProductRepository;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CategoryDao implements ICategoryDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Transactional
    @Override
    public void persist(CategoryEntity category) {

        entityManager.persist(category);
    }

    @Override
    public CategoryEntity findById(Long id) {
        CategoryEntity categoryEntity = entityManager.find(CategoryEntity.class, id);
        return categoryEntity;
    }
@Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(CategoryEntity.class, id));
    }

    @Override
    public List<CategoryEntity> findAllRepo() {
        return iCategoryRepository.findAll();
    }

    @Transactional
    @Override
    public CategoryEntity findByIdRepo(Long id) {
        return iCategoryRepository.findById(id).get();
    }

    @Transactional
    @Override
    public List findCategoryNativeById(Long id) {
        String sql = " SELECT category.name,category.id as ids ,product.id,product.title,product.price  FROM category left JOIN product ON  category.id=product.category_id";
        Query typedQuery = entityManager.createNativeQuery(sql);
        List categoryEntities = typedQuery.getResultList();

        String sql3 = " SELECT *  FROM category ";
        Query typedQuery2 = entityManager.createNativeQuery(sql3);
        List categoryEntities2 = typedQuery2.getResultList();


        String sql4 = " SELECT *  FROM category ";
        TypedQuery<CategoryEntity> typedQuery4 = (TypedQuery<CategoryEntity>) entityManager.createNativeQuery(sql4, CategoryEntity.class);
        List<CategoryEntity> categoryEntities4 = typedQuery4.getResultList();
        for (CategoryEntity cate : categoryEntities4) {
            System.out.println(cate.getProducts());
        }
        ProductEntity product = entityManager.find(ProductEntity.class, 1L);
        System.out.println(product.getName());
        String sql1 = " update product p set p.name=:name,p.title=:title,p.price=:price where p.id=:id";
        int query = entityManager.createNativeQuery(sql1)
                .setParameter("name", "so")
                .setParameter("title", "tfk1")
                .setParameter("price", 100L)
                .setParameter("id", 1L)
                .executeUpdate();
        entityManager.flush();
//entityManager.clear();
        ProductEntity product1 = entityManager.find(ProductEntity.class, 1L);
        System.out.println(product1.getName());
//entityManager.contains(categoryEntities);//có @T thì là true, còn không là true
        String sql2 = "SELECT * FROM category ";
        TypedQuery<CategoryEntity> typedQuery1 = (TypedQuery<CategoryEntity>) entityManager.createNativeQuery(sql2, CategoryEntity.class);
        List<CategoryEntity> categoryEntitiess = typedQuery1.getResultList();
        return categoryEntitiess;// đcm có lặp cho duf cos @T hay khoong
    }
//thằng sqlNative vẫn có session như thường
// nếu đặt @T session k mắt, mở OSIV cũng k mất, trong @T thay đổi được dữ liệu
}
