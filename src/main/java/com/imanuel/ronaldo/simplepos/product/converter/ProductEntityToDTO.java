package com.imanuel.ronaldo.simplepos.product.converter;

import com.imanuel.ronaldo.simplepos.product.dto.ProductDTO;
import com.imanuel.ronaldo.simplepos.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductEntityToDTO {
    ProductEntityToDTO INSTANCE = Mappers.getMapper(ProductEntityToDTO.class);
    ProductDTO productToProductDTO(Product product);
}
