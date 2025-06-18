package com.bitsnbytes.controller;

import com.bitsnbytes.dto.ProductDTO;
import com.bitsnbytes.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Product REST API CRUD Operation",
        description = "CREATE, READ, UPDATE and DELETE operations for Product REST API"
)
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //create product
    @Operation(
            summary = "Creates a product",
            description = "REST API for Creating a product"
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
       return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    //get all products
    @Operation(
            summary = "Fetch all products",
            description = "REST API for fetching all products"
    )
    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }
    //get product by id
    @Operation(
            summary = "Fetch product by id",
            description = "REST API for fetching product by id"
    )
    @GetMapping("/{id}")
    public ProductDTO getProductByid(@PathVariable Long id){
        return productService.getProductByid(id);
    }

    //delete product by id
    @Operation(
            summary = "Delete product by id",
            description = "REST API for Deleting product by id"
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id){
        return productService.deleteProductById(id);
    }

    //update product
    @Operation(
            summary = "Update by id",
            description = "REST API for updating product by id"
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO){
        return productService.updateProduct(id, productDTO);
    }
}
