package com.imanuel.ronaldo.simplepos.product.service;

import com.imanuel.ronaldo.simplepos.product.entity.Product;
import com.imanuel.ronaldo.simplepos.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProduct(Pageable pageable){
        return productRepository.findAll(pageable);
    }
}
