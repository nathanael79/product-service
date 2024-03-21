package com.imanuel.ronaldo.simplepos.product.controller;

import com.imanuel.ronaldo.simplepos.constants.ResourcePath;
import com.imanuel.ronaldo.simplepos.product.converter.ProductEntityToDTO;
import com.imanuel.ronaldo.simplepos.product.dto.ProductDTO;
import com.imanuel.ronaldo.simplepos.product.entity.Product;
import com.imanuel.ronaldo.simplepos.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;


@RestController
@RequestMapping(value = ResourcePath.BASE_PATH_API_URL+ResourcePath.API_VERSION_1)
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = ResourcePath.PRODUCT_LIST_URL)
    public ResponseEntity<Page<ProductDTO>> getAllProductBySearchAndSort(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String orderBy
    ){
        Sort sort = orderBy.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> products = productService.getAllProduct(pageable);

        Page<ProductDTO> productDTOS = products.map(new Function<Product, ProductDTO>() {
            @Override
            public ProductDTO apply(Product product) {
                return ProductEntityToDTO.INSTANCE.productToProductDTO(product);
            }
        });

        return ResponseEntity.ok().body(productDTOS);
    }



}
