package com.imanuel.ronaldo.simplepos.service;

import com.imanuel.ronaldo.simplepos.exception.ResourceNotFoundException;
import com.imanuel.ronaldo.simplepos.product.dto.ProductDTO;
import com.imanuel.ronaldo.simplepos.product.entity.Product;
import com.imanuel.ronaldo.simplepos.product.repository.ProductRepository;
import com.imanuel.ronaldo.simplepos.product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;
    @Test
    @DisplayName("GET One product")
    void getOneProductByIdTest() {
        Long id = Long.valueOf(1);
        Product product = new Product(id, "prox2xduct1", BigInteger.valueOf(10000), false);

        // Stubbing the findById method to return an Optional of the product
        given(productRepository.findById(id)).willReturn(Optional.of(product));

        // Calling the tested method
        Product productTest = productService.getProduct(id);

        // Assertions
        assertThat(productTest).isNotNull();
    }


    @Test
    @DisplayName("GET One product and failed")
    void getOneProductByIdTestFailedCase() {
        Long id = Long.valueOf(1);

        // Stubbing the findById method to return an Optional of the product
        given(productRepository.findById(id)).willThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            // Calling the tested method
            Product productTest = productService.getProduct(id);
        });


    }

    @Test
    @DisplayName("GET All Product with search and criteria")
    void getAllProductWithSearchAndSort() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").ascending());

        List<Product> productList = Arrays.asList(
                new Product(Long.valueOf(1), "product1", BigInteger.valueOf(10000), false),
                new Product(Long.valueOf(1), "product2", BigInteger.valueOf(10000), false),
                new Product(Long.valueOf(1), "product3", BigInteger.valueOf(10000), false)
        );

        Page<Product> products = new PageImpl<>(productList, pageable, productList.size());
        when(productRepository.findAll(pageable)).thenReturn(products);

        Page<Product> productTest = productService.getAllProduct(pageable);

        assertEquals(products, productTest);
    }

    @Test
    @DisplayName("Create a product and verify is the method called true")
    void createProductTest() {
        // Prepare test data
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("product1");
        productDTO.setPrice(BigInteger.valueOf(10000));

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setIsActive(true);

        // Stub repository method
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Call the tested method
        Product result = productService.createProduct(productDTO);

        // Verify that the repository method is called with the correct argument
        verify(productRepository).save(any(Product.class));
    }

    @Test
    @DisplayName("Create a product and verify is assert null")
    void createProductTestAndAssertNull() {
        // Prepare test data
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("product1");
        productDTO.setPrice(BigInteger.valueOf(10000));

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setIsActive(true);

        // Stub repository method
        when(productRepository.save(any(Product.class))).thenReturn(null);

        // Call the tested method
        Product result = productService.createProduct(productDTO);
        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("Update a product and check the method is true")
    void updateProductTest() {
            // Prepare test data
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("product1new");
        productDTO.setPrice(BigInteger.valueOf(12000));

        Product existingProduct = new Product();
        existingProduct.setId(Long.valueOf(1));
        existingProduct.setName("product1");
        existingProduct.setPrice(BigInteger.valueOf(10000));

        Product updatedProduct = new Product();
        updatedProduct.setId(Long.valueOf(1));
        updatedProduct.setName("product1new");
        updatedProduct.setPrice(BigInteger.valueOf(12000));

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

          // Call the tested method
        Product result = productService.updateProduct(Long.valueOf(1), productDTO);

        // Assertions
        Assertions.assertNotNull(result);
        Assertions.assertEquals(productDTO.getName(), result.getName());
        Assertions.assertEquals(productDTO.getPrice(), result.getPrice());
    }

    @Test
    @DisplayName("Delete a product")
    void deleteProductTest() {
        Long id = Long.valueOf(1);

        Product existedProduct = new Product();
        existedProduct.setId(id);
        existedProduct.setName("product1");
        existedProduct.setPrice(BigInteger.valueOf(10000));
        existedProduct.setIsActive(true);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(existedProduct));
        doNothing().when(productRepository).delete(any(Product.class));

        productService.deleteProduct(id);

        verify(productRepository, times(1)).delete(existedProduct);
    }
}
