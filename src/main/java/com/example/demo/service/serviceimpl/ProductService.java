package com.example.demo.service.serviceimpl;

import com.example.demo.dao.IProductDao;
import com.example.demo.dto.Result;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return iProductDao.save(productEntity);
    }

    @Override
    public ProductEntity findById(Long id) throws InterruptedException {
        ProductEntity product = iProductRepository.findById(id).get();
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


        Thread.sleep(15000);

        product.setTitle("!23123123123");
        iProductRepository.save(product);
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
