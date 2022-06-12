package com.proj.demo.service;

import com.proj.demo.dto.ProductDTO;
import com.proj.demo.entity.Product;
import com.proj.demo.exception.NoSuchProductException;
import com.proj.demo.repository.ProductRepository;
import com.proj.demo.service.mapper.ProductDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.service.CRUD;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductService implements CRUD<ProductDTO> {

    ProductDTOMapper productMapper;
    ProductRepository productRepository;
    ModelMapper mapper;

    public Page<ProductDTO> findAll(Pageable pageable) {
        return productRepository.findAllByDeletedFalse(pageable).map(productMapper::toDTO);
    }

    @Override
    public void create(ProductDTO dto) throws Exception {
        productRepository.save(productMapper.toEntity(dto));
    }

    @Override
    public ProductDTO findByCode(String code) throws Exception {
        Product product = productRepository.findByCodeAndDeletedFalse(Integer.parseInt(code)).orElseThrow(NoSuchProductException::new);
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO update(ProductDTO dto) throws Exception {
        Product product = productRepository.findByCodeAndDeletedFalse(dto.getCode()).orElseThrow(NoSuchProductException::new);
        Product newProduct = productMapper.toEntity(dto);
        mapper.map(newProduct, product);
        Product save = productRepository.save(product);
        return productMapper.toDTO(save);
    }

    @Override
    public void delete(Long code) throws Exception {
        Product product = productRepository.findByCodeAndDeletedFalse(code.intValue()).orElseThrow(NoSuchProductException::new);
        product.setDeleted(true);
        productRepository.save(product);
    }
}
