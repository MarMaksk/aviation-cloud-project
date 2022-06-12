package com.proj.demo.service.mapper;

import com.proj.demo.dto.ProductDTO;
import com.proj.demo.dto.TagDTO;
import com.proj.demo.entity.Product;
import com.proj.demo.entity.Tag;
import com.proj.demo.repository.TagRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductDTOMapper implements EntityToDTOMapper<Product, ProductDTO> {

    ModelMapper mapper;
    TagRepository tagRepository;

    @Override
    public Product toEntity(ProductDTO dto) {
        Product entity = mapper.map(dto, Product.class);
        entity.setTags(dto.getTag().stream()
                .map(TagDTO::getName)
                .map(tagRepository::findByName)
                .map(Optional::get)
                .collect(Collectors.toSet()));
        System.out.println(entity);
        return entity;
    }

    @Override
    public ProductDTO toDTO(Product entity) {
        ProductDTO dto = mapper.map(entity, ProductDTO.class);
        dto.setTag(entity.getTags().stream()
                .map(Tag::getName)
                .map(TagDTO::new)
                .collect(Collectors.toSet()));
        System.out.println(dto);
        return dto;
    }
}
