package com.bitsnbytes.controller;

import com.bitsnbytes.dto.ProductDTO;
import com.bitsnbytes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //create product
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
       return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    //get all products
    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    //get product by id
    @GetMapping("/{id}")
    public ProductDTO getProductByid(@PathVariable Long id){
        return productService.getProductByid(id);
    }

    //delete product by id
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id){
        return productService.deleteProductById(id);
    }

    //update product
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO){
        return productService.updateProduct(id, productDTO);
    }
}
