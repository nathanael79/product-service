package com.imanuel.ronaldo.simplepos.product.entity;

import com.imanuel.ronaldo.simplepos.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Data
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigInteger price;

    private Boolean isActive;

}
