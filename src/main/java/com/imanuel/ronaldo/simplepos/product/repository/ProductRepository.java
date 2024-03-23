package com.imanuel.ronaldo.simplepos.product.repository;

import com.imanuel.ronaldo.simplepos.product.entity.Product;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
