package com.bitsnbytes.service;

import com.bitsnbytes.dto.CategoryDTO;
import com.bitsnbytes.entity.Category;
import com.bitsnbytes.mapper.CategoryMapper;
import com.bitsnbytes.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    //create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category=categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }

    //get all categories
    public List<CategoryDTO> getAllCategories(){
       return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }

    //get category By id
    public CategoryDTO getCategoryById(Long id){
        Category category= categoryRepository.findById(id).orElseThrow(()->new RuntimeException("category not found"));
        return CategoryMapper.toCategoryDTO(category);
    }

    //delete category by id
    public String deleteCategoryById(Long id){
         categoryRepository.deleteById(id);
         return "category deleted successfully....";
    }
}
