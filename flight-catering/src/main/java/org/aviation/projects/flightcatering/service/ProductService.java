package org.aviation.projects.flightcatering.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.service.CRUD;
import org.aviation.projects.flightcatering.dto.ProductDTO;
import org.aviation.projects.flightcatering.entity.Product;
import org.aviation.projects.flightcatering.exception.NoSuchProductException;
import org.aviation.projects.flightcatering.repository.ProductRepository;
import org.aviation.projects.flightcatering.service.mapper.ProductDTOMapper;
import org.jfree.util.Log;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductService implements CRUD<ProductDTO> {

    ProductDTOMapper productMapper;
    ProductRepository productRepository;
    ModelMapper mapper;

    @Transactional
    public Page<ProductDTO> findAll(Pageable pageable) {
        Log.info("findAll method called in ProductService");
        return productRepository.findAllByDeletedFalse(pageable).map(productMapper::toDTO);
    }

    @Transactional
    public List<ProductDTO> findAll() {
        Log.info("findAll method called in ProductService");
        return productMapper.toDTOs(productRepository.findAll());
    }

    @Override
    public void create(ProductDTO dto) throws Exception {
        Log.info("create method called in ProductService");
        int MIN_GEN_VAL = 10000000;
        int MAX_GEN_VAL = 99999999;
        dto.setCode(new Random().nextInt(MAX_GEN_VAL - MIN_GEN_VAL) + MIN_GEN_VAL);
        productRepository.save(productMapper.toEntity(dto));
    }

    @Override
    public ProductDTO findByCode(String code) throws Exception {
        Log.info("findByCode method called in ProductService");
        Product product = productRepository.findByCodeAndDeletedFalse(Integer.parseInt(code)).orElseThrow(NoSuchProductException::new);
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO update(ProductDTO dto) throws Exception {
        Log.info("update method called in ProductService");
        Product product = productRepository.findByCodeAndDeletedFalse(dto.getCode()).orElseThrow(NoSuchProductException::new);
        Product newProduct = productMapper.toEntity(dto);
        mapper.map(newProduct, product);
        Product save = productRepository.save(product);
        return productMapper.toDTO(save);
    }

    @Override
    public void delete(String code) throws Exception {
        Log.info("delete method called in ProductService");
        Product product = productRepository.findByCodeAndDeletedFalse(Integer.parseInt(code)).orElseThrow(NoSuchProductException::new);
        product.setDeleted(true);
        productRepository.save(product);
    }
}
