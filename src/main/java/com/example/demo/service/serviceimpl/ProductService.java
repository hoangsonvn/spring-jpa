package com.example.demo.service.serviceimpl;

import com.example.demo.dao.IProductDao;
import com.example.demo.dto.Result;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class ProductService implements IProductService {
    @Autowired
    private IProductDao iProductDao;
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public void persist(ProductEntity productEntity) {
        iProductDao.persist(productEntity);
    }

    @Override
    public ProductEntity findByIdEF(Long id) {

        return iProductDao.findByIdEF(id);
    }

    @Transactional
    public void saveMany() {
        ProductEntity productEntity = new ProductEntity();
        ProductEntity productEntity1 = new ProductEntity();
        ProductEntity productEntity2 = new ProductEntity();
        ProductEntity productEntity3 = new ProductEntity();
        ProductEntity productEntity4 = new ProductEntity();
        ProductEntity productEntity5 = new ProductEntity();
        ProductEntity productEntity6 = new ProductEntity();
        ProductEntity productEntity7 = new ProductEntity();
        ProductEntity productEntity8 = new ProductEntity();
        ProductEntity productEntity9 = new ProductEntity();
        ProductEntity productEntity10 = new ProductEntity();
        productEntity.setName(String.valueOf(new Date().getTime()));
        productEntity1.setName(String.valueOf(new Date().getTime()));
        productEntity2.setName(String.valueOf(new Date().getTime()));
        productEntity3.setName(String.valueOf(new Date().getTime()));
        productEntity4.setName(String.valueOf(new Date().getTime()));
        productEntity5.setName(String.valueOf(new Date().getTime()));
        productEntity6.setName(String.valueOf(new Date().getTime()));
        productEntity7.setName(String.valueOf(new Date().getTime()));
        productEntity8.setName(String.valueOf(new Date().getTime()));


        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(productEntity);
        productEntityList.add(productEntity1);
        productEntityList.add(productEntity2);
        productEntityList.add(productEntity3);
        productEntityList.add(productEntity4);
        productEntityList.add(productEntity5);
        productEntityList.add(productEntity6);
        productEntityList.add(productEntity7);
        productEntityList.add(productEntity8);
        productEntityList.add(productEntity9);
        productEntityList.add(productEntity10);

        for (ProductEntity entity : productEntityList) {
//            CompletableFuture.runAsync(() -> {
//                entity.setPrice(new Date().getTime());
//                productService.save(entity);
//            });
            new Thread(() -> {
                entity.setPrice(new Date().getTime());
                iProductRepository.save(entity);
            }).start();
        }


//        for (int i = 1; i < 10; i++) {
//            CompletableFuture.runAsync(() -> {
//                String data = String.valueOf(new Date().getTime());
//                product.setName(data);
//                productService.save(product);
//            });
//        }
//        return null;
    }

    @Transactional
    public void updateProduct(Long id) throws InterruptedException {
        ProductEntity product = iProductRepository.findById(id).get();
        product.setPrice(new Date().getTime());
        System.out.println("-------------------------------------------------" + new Date().getTime());
        iProductRepository.save(product);
        Thread.sleep(5000);
    }


    @Override
    @Transactional
    public ProductEntity save(ProductEntity productEntity) {
        ProductEntity product = iProductDao.save(productEntity);
        System.out.println(product.getId());
        return product;
    }

    @Transactional
    public void saveNew(ProductEntity productEntity) throws InterruptedException {
        ProductEntity product = iProductDao.save(productEntity);
        Thread.sleep(3000);
        product.setPrice(new Date().getTime());
        iProductRepository.findById(400L).get();


    }

    public void saveNewNoT(ProductEntity productEntity) throws InterruptedException {
        ProductEntity product = iProductDao.save(productEntity);
        Thread.sleep(3000);
        product.setPrice(new Date().getTime());
        iProductRepository.findById(400L).get();
    }

    @Override
    public ProductEntity findById(Long id) throws InterruptedException {
//        ProductEntity product = iProductRepository.findById(id).get();
//        product.setTitle("1223");
//        ProductEntity productEntity = iProductRepository.save(product);
//        productEntity.setTitle("3232");
//        iProductDao.findById(id);
//iProductRepository.findByCode("1223");
//        iProductRepository.findByCode("1223");
//        iProductRepository.findByCode("1223");
//        iProductRepository.findByCode("1223");

//        iProductDao.findById(id);
//        iProductRepository.findById(id);


//        Thread.sleep(15000);

//        product.setTitle("!23123123123");
//        iProductRepository.save(product);
        return iProductRepository.findById(id).get();
    }

    @Override
    public List<ProductEntity> findAll() {

        return iProductDao.findAll();
    }

    @Override
    public List<Object[]> findNameAndCode() {
        return iProductDao.findNameAndCode();
    }

    @Override
    public List<Result> findNameAndCodeCustomClass(Long id) {
        return iProductDao.findNameAndCodeCustomClass(id);
    }
}
