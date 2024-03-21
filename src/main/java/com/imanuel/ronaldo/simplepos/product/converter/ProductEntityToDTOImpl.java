package com.imanuel.ronaldo.simplepos.product.converter;

import com.imanuel.ronaldo.simplepos.product.dto.ProductDTO;
import com.imanuel.ronaldo.simplepos.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToDTOImpl implements ProductEntityToDTO{
    @Override
    public ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }
}
