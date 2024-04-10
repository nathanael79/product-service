package com.imanuel.ronaldo.simplepos.product.service;

import com.imanuel.ronaldo.simplepos.exception.ResourceNotFoundException;
import com.imanuel.ronaldo.simplepos.product.dto.ProductDTO;
import com.imanuel.ronaldo.simplepos.product.entity.Product;
import com.imanuel.ronaldo.simplepos.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProduct(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Product getProduct(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with these id is not found: "+id));
    }

    public Product createProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setIsActive(true);

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDTO productDTO){
        Product product = getProduct(id);

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setIsActive(true);

        return productRepository.save(product);
    }
}
