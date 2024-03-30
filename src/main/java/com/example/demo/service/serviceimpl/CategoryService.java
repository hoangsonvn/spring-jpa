package com.example.demo.service.serviceimpl;

import com.example.demo.dao.ICategoryDao;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ICategoryRepository;
import com.example.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
@Service
public class CategoryService implements ICategoryService {
private long b = 1;

    public Long getLongmethods(){
//        iCategoryDao.findCategoryNativeById(2l);
        b++;
        System.out.println(b+"categpry");
        return b;
    }
    @Autowired
    private  ICategoryRepository iCategoryRepository;
    @Autowired
    private  ICategoryDao iCategoryDao;

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


    //    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional
    public int saveManyProducts(long id) {
//        try {
//        CategoryEntity categoryEntity = iCategoryRepository.findById(id).orElse(new CategoryEntity());
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
            System.out.println(status);
        }
         saveMona(id);
        System.out.println("saveManyProducts");
        return 1;
//        } catch (Exception e) {
//            System.out.println("do save");
//        }
    }

    //    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional
    public void saveMona(long id) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
            System.out.println(status);
        }
//        saveManyProducts(id);
//        try {

        CategoryEntity categoryEntity = iCategoryRepository.findById(id).orElse(new CategoryEntity());

        int random = (int) (Math.random() * 50 + 1);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("van" + random);
        productEntity.setTitle(String.valueOf(random));

        categoryEntity.addProduct(productEntity);
        iCategoryRepository.save(categoryEntity);
        int random1 = (int) (Math.random() * 50 + 1);

        ProductEntity productEntit1 = new ProductEntity();
        productEntit1.setName("van" + random1);
        productEntit1.setTitle(String.valueOf(random1));
        categoryEntity.addProduct(productEntit1);
        iCategoryRepository.save(categoryEntity);
        throw new RuntimeException("Runtime exception");
//    throw new Exception("2");
//        } catch (Exception e) { // try catch thi các đoạn code thực thi trong try catch không rollback lại ,
//            // tại sao ném new Exception trong transactinal không có try catch mà khong rollback, bởi vì exception này là chung
//            // có 2 lớp kế thừa là runTimeException(hay còn gọi là uncheck exception, và còn lại đc gọi là checked exception ,
//            // việc new Exception() được xem như checked exception nên không rollback
//            System.out.println("come in");
//        }
    }
}
