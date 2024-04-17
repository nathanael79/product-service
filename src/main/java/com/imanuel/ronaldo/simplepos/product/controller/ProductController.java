package com.imanuel.ronaldo.simplepos.product.controller;

import com.imanuel.ronaldo.simplepos.component.CustomPageRequest;
import com.imanuel.ronaldo.simplepos.constants.ResourcePath;
import com.imanuel.ronaldo.simplepos.product.converter.ProductEntityToDTO;
import com.imanuel.ronaldo.simplepos.product.dto.ProductDTO;
import com.imanuel.ronaldo.simplepos.product.entity.Product;
import com.imanuel.ronaldo.simplepos.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;


@RestController
@RequestMapping(value = ResourcePath.BASE_PATH_API_URL+ResourcePath.API_VERSION_1)
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = ResourcePath.PRODUCT_GET_ONE_URL)
    public ResponseEntity<ProductDTO> getOneProduct(
            @PathVariable(required = true) Long id
    ){
        Product product = productService.getProduct(id);
        ProductDTO productDTO = ProductEntityToDTO.INSTANCE.productToProductDTO(product);
        return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping(value = ResourcePath.PRODUCT_LIST_URL)
    public ResponseEntity<Page<ProductDTO>> getAllProductBySearchAndSort(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String orderBy
    ){
        Sort sort = orderBy.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = new CustomPageRequest(pageNo, pageSize, sort);
        Page<Product> products = productService.getAllProduct(pageable);

        Page<ProductDTO> productDTOS = products.map(new Function<Product, ProductDTO>() {
            @Override
            public ProductDTO apply(Product product) {
                return ProductEntityToDTO.INSTANCE.productToProductDTO(product);
            }
        });

        return ResponseEntity.ok().body(productDTOS);
    }

    @PostMapping(value = ResourcePath.PRODUCT_CREATE_URL)
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody ProductDTO productDTO
    ){
        ProductDTO newProduct = ProductEntityToDTO.INSTANCE.productToProductDTO(productService.createProduct(productDTO));
        return ResponseEntity.ok().body(newProduct);
    }

    @PatchMapping(value = ResourcePath.PRODUCT_UPDATE_URL)
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable(required = true) Long id,
            @RequestBody(required = true) ProductDTO productDTO
    ){
        ProductDTO updatedProduct = ProductEntityToDTO.INSTANCE.productToProductDTO(productService.updateProduct(id, productDTO));
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping(value = ResourcePath.PRODUCT_DELETE_URL)
    public ResponseEntity<?> deleteProduct(
        @PathVariable(required = true) Long id
    ){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
