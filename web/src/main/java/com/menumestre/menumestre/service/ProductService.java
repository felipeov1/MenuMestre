package com.menumestre.menumestre.service;

import com.menumestre.menumestre.domain.product.Product;
import com.menumestre.menumestre.domain.product.ProductRequestDTO;
import com.menumestre.menumestre.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Product createProduct(ProductRequestDTO productRequestDTO) {
        Product newProduct = new Product();
        newProduct.setName(productRequestDTO.name());
        newProduct.setPrice(productRequestDTO.price());

        return productRepository.save(newProduct);
    }
}
