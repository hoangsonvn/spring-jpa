package com.example.demo.service.serviceimpl;

import com.example.demo.dao.IProductDao;
import com.example.demo.dto.Result;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class ProductService implements IProductService {
    @Autowired
    private IProductDao iProductDao;

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
@Transactional
    @Override
    public ProductEntity findById(Long id) {
        return iProductDao.findById(id);
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
