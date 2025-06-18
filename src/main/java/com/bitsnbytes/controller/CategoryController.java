package com.bitsnbytes.controller;

import com.bitsnbytes.dto.CategoryDTO;
import com.bitsnbytes.exception.CategoryAlreadyExistsException;
import com.bitsnbytes.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Category REST API CRUD Operation",
        description = "CREATE, READ, UPDATE and DELETE operations for Category REST API"
)
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //create category
    @Operation(
            summary = "Creates a category",
            description = "REST API for Creating a category"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    //get all categories
    @Operation(
            summary = "Fetch all categories along with products",
            description = "REST API for fetching all categories along with products"
    )
    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    //get category by id
    @Operation(
            summary = "Fetch category by id",
            description = "REST API for fetching category by id"
    )
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    //delete category by id
    @Operation(
            summary = "Delete Category by id",
            description = "REST API for Deleting Category by id"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable Long id){
        return categoryService.deleteCategoryById(id);
    }
}
