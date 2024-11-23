package com.menumestre.menumestre.controller;

import com.menumestre.menumestre.domain.product.Product;
import com.menumestre.menumestre.domain.product.ProductRequestDTO;
import com.menumestre.menumestre.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menumestre")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        Product createdProduct = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(createdProduct);
    }
}