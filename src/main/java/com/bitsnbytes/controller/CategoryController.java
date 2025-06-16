package com.bitsnbytes.controller;

import com.bitsnbytes.dto.CategoryDTO;
import com.bitsnbytes.exception.CategoryAlreadyExistsException;
import com.bitsnbytes.service.CategoryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //create category
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    //get all categories
    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    //get category by id
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    //delete category by id
    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable Long id){
        return categoryService.deleteCategoryById(id);
    }
}
