package com.menumestre.menumestre.repositories;

import com.menumestre.menumestre.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ProductRepository extends JpaRepository<Product, UUID> { }
