package com.proj.demo.service;

import com.proj.demo.service.mapper.ProductDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductDTOMapper productMapper;
}
