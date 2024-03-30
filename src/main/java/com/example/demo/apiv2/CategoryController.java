package com.example.demo.apiv2;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ICategoryRepository;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.serviceimpl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ICategoryRepository iCategoryRepository;
    final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private long a = 0;
//    @GetMapping("/category")
//    @ResponseBody
//    public ResponseEntity findAllRepo() {
//        return ResponseEntity.ok(iCategoryService.findAllRepo());
//    }

//    @PostMapping("/category")
//    @ResponseBody
//    public ResponseEntity persist(@RequestBody CategoryEntity category) {
//        iCategoryService.persist(category);
//        return ResponseEntity.ok(category);
//    }

//    @GetMapping("/category/{id}")
//    @ResponseBody
//    public CategoryEntity findById(@PathVariable Long id) {
//        CategoryEntity category = iCategoryService.findById(id);
//        return category;
//    }

    @DeleteMapping("/category")
    @ResponseBody
    public ResponseEntity delete(@RequestBody Long[] ids) {
        for (Long id : ids
        ) {
            iCategoryService.delete(id);
        }
        return ResponseEntity.ok("done");
    }

    @GetMapping("/sql/category/{id}")
    @ResponseBody
    public ResponseEntity findCategoryNativeById(@PathVariable(value = "id") Long id) {
        ResponseEntity.ok(iCategoryService.findCategoryNativeById(id));
        return null;
    }

    @GetMapping("/category/{id}")
    @ResponseBody
    public CategoryEntity findCategory(@PathVariable(value = "id") Long id) {
        CategoryEntity category = iCategoryRepository.findById(id).orElse(new CategoryEntity());
        return category;
    }

    @PostMapping("/category")
    @ResponseBody
    public CategoryEntity updateCategory(@RequestBody CategoryEntity categoryEntity) {
        return iCategoryRepository.save(categoryEntity);
    }

    @PostMapping("/cate/nocasade/add")
    public ResponseEntity save(@RequestParam long id, HttpServletRequest request) {

        System.out.println(categoryService.getLongmethods());
//CategoryService categoryService = new CategoryService(iCategoryRepository, iCategoryDao);
//        System.out.println(categoryService.getLongmethods());


        System.out.println(Thread.currentThread().getId());
        a++;
        System.out.println(a);
//        try {
////            executorService.execute(() -> {
//            categoryService.saveManyProducts(id);
//
////            });
////            CompletableFuture.runAsync(() -> iCategoryService.saveManyProducts(id)).exceptionally(ex -> {
////                System.out.println("Oops! We have an exception - " + ex.getMessage());
////                return null;
////            });
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        return ResponseEntity.ok("done");
    }

    @PostMapping("/cate/nocasade")
    public ResponseEntity save1(@RequestBody CategoryEntity categoryEntity, HttpServletRequest request) {
        int random = (int) (Math.random() * 50 + 1);
        ProductEntity productEntity = new ProductEntity();

        productEntity.setName("van" + random);
        productEntity.setTitle(String.valueOf(random));
        List<ProductEntity> products = new ArrayList<>();
        products.add(productEntity);
        categoryEntity.setProducts(products);
        iCategoryRepository.save(categoryEntity);
        return ResponseEntity.ok(iCategoryRepository.save(categoryEntity));
    }

}
