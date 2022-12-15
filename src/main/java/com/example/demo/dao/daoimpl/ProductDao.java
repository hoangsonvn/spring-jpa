package com.example.demo.dao.daoimpl;

import com.example.demo.dao.IProductDao;
import com.example.demo.dto.Result;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.IProductRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class ProductDao implements IProductDao {

    @Autowired
    private IProductRepository iProductRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(ProductEntity productEntity) {
        entityManager.persist(productEntity);
    }

    @Transactional
    public List<ProductEntity> findAll() {
        String sql = "SELECT u FROM ProductEntity u where u.id = :id ";
        // entityManager.getEntityManagerFactory().getCache().contains()
        Session session = entityManager.unwrap(Session.class);
        TypedQuery<ProductEntity> query = session.createQuery(sql, ProductEntity.class).setParameter("id", 1L);
        ProductEntity productEntities = query.setHint("org.hibernate.cacheable", true).getSingleResult();
        System.out.println(entityManager.contains(productEntities));
        productEntities.setName("ns1");
// theo như debug khi không @T session mở ra rồi đóng lại khi chạy xong lệnh . getSingleResult
// , dù trong 1 method cũng đóng 2 lần chứ không phải cuối methods như cái khác

        String sql1 = "SELECT u FROM ProductEntity u left  JOIN u.category p ";
        TypedQuery<ProductEntity> query1 = session.createQuery(sql1, ProductEntity.class);
        List<ProductEntity> productEntities1 = query1.getResultList();//và đây cũng là câu lệnh như flush ấy đẩy hết lên DB , nên đẩy luôn cả lệnh update trên kia nếu đang dùng @T
        // tại sao giống nhau nhưng nó vẫn chạy ra lặp mặc dù có @T? vì sql nó biết cc gì được, hãy note lại cái query cache nha!! thử sau dùng xem lệnh có lặp nữa không
        //  System.out.println(entityManager.contains(productEntities1)); //fasle khi không có @T, lệnh này check an entity không phải list nhưng vẫn có list trong persistentcontext
        return productEntities1;
    }

    //có @T session cũng không đóng
    // khi bật OSIV thì session không close, vẫn chỉ 1 session từ OSIV xuyên suốt 1 request
    @Override
    public List<Object[]> findNameAndCode() {
        String jpql = "SELECT u.name, p.code FROM ProductEntity u JOIN  u.category p";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> list = query.getResultList();
        return list;
    }

    @Override
    public List<Result> findNameAndCodeCustomClass(Long id) {
        String hql = "select new com.example.demo.dto.Result(u.name,u.category.code) " +
                " from ProductEntity u join u.category p" +
                " where u.id=:id";
        TypedQuery<Result> typedQuery = entityManager.createQuery(hql, Result.class)
                .setParameter("id", id);
        List<Result> results = typedQuery.getResultList();
        //    System.out.println(entityManager.contains(typedQuery.getResultList()));
        return results;
    }

  /*  @Override
    @Transactional
    public ProductEntity findByIdEF(Long id) {
        ProductEntity productEntity = entityManager.find(ProductEntity.class, id);
        return productEntity;
    }*/
    @Override
    @Transactional
    public ProductEntity findByIdEF(Long id) {
  //   String sql = "SELECT p FROM ProductEntity p JOIN p.category c WHERE p.id= :id ";
       String sql = "SELECT p FROM ProductEntity p JOIN FETCH  p.category WHERE p.id= :id ";
        TypedQuery<ProductEntity> typedQuery = entityManager.createQuery(sql,ProductEntity.class).setParameter("id",id);
        ProductEntity product = typedQuery.getSingleResult();
        product.getCategory().getName();
        return product;
    }

    public ProductEntity save(ProductEntity productEntity) {
        return iProductRepository.save(productEntity);
    }

    /* public ProductEntity findById(Long id) {
         return iProductRepository.findById(id).get();
     }*/
    public ProductEntity findById(Long id) {
        TypedQuery<ProductEntity> typedQuery = entityManager.createQuery("SELECT p FROM ProductEntity p WHERE p.id =:id AND p.status=true ", ProductEntity.class)
                .setParameter("id", id);
         typedQuery.getSingleResult();
         return null;
    }
}