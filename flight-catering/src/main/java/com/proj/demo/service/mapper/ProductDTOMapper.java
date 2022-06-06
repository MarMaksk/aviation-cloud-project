package com.proj.demo.service.mapper;

import com.proj.demo.dto.ProductDTO;
import com.proj.demo.entity.Product;
import org.aviation.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDTOMapper implements EntityToDTOMapper<Product, ProductDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Product toEntity(ProductDTO dto) {
        return mapper.map(dto, Product.class);
    }

    @Override
    public ProductDTO toDTO(Product entity) {
        return mapper.map(entity, ProductDTO.class);
    }
}
