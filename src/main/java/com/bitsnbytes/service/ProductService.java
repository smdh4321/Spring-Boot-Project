package com.bitsnbytes.service;

import com.bitsnbytes.dto.ProductDTO;
import com.bitsnbytes.entity.Category;
import com.bitsnbytes.entity.Product;
import com.bitsnbytes.exception.CategoryNotFoundException;
import com.bitsnbytes.mapper.ProductMapper;
import com.bitsnbytes.repository.CategoryRepository;
import com.bitsnbytes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    //create product
    public ProductDTO createProduct(ProductDTO productDTO){
        //check the category is present or not in DB
        Category category=categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()-> new CategoryNotFoundException("category id: " +productDTO.getCategoryId()+" not found"));
        //DTO to entity
        Product product=ProductMapper.toProductEntity(productDTO,category);
        product=productRepository.save(product);

        //Entity to DTO
        return ProductMapper.toProductDTO(product);

    }

    //get all products

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map((ProductMapper::toProductDTO)).toList();
    }

    //get product by id
    public ProductDTO getProductByid(Long id){
        Product product= productRepository.findById(id).orElseThrow(()->new RuntimeException("product not found"));
        return ProductMapper.toProductDTO(product);
    }

    //delete product by id
    public String deleteProductById(Long id){
        productRepository.deleteById(id);
        return "Product deleted Successfully...";
    }

    //update product
    public ProductDTO updateProduct(Long id,ProductDTO productDTO){
        Product product= productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("product not found"));
        Category category=categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()->new RuntimeException("category not found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toProductDTO(product);

    }
}
