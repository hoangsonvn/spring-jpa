package com.example.demo.apiv2;

import com.example.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/annotation")
//@RequiredArgsConstructor
public class AnotationCuncurrentController {
    @Autowired
    private ICategoryService iCategoryService;

//    public AnotationCuncurrentController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }


    public AnotationCuncurrentController() {
    }

    @GetMapping("")
    public ResponseEntity getAno(){
        System.out.println(iCategoryService);
        return  null;
      //  CategoryService categoryService1 = new CategoryService(iCategoryRepository, iCategoryDao);
//        System.out.println(categoryService.toString());
//        categoryService1.findById(2l);
//        return ResponseEntity.ok(iCategoryService.toString()+categoryService1.toString());
    }
}
