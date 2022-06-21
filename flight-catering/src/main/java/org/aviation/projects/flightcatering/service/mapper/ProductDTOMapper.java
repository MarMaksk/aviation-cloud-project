package org.aviation.projects.flightcatering.service.mapper;

import org.aviation.projects.flightcatering.dto.ProductDTO;
import org.aviation.projects.flightcatering.dto.TagDTO;
import org.aviation.projects.flightcatering.entity.Product;
import org.aviation.projects.flightcatering.entity.Tag;
import org.aviation.projects.flightcatering.repository.TagRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.service.mapper.EntityToDTOMapper;
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
        entity.setTags(dto.getTags().stream()
                .map(tag -> {
                    Optional<Tag> optionalTag = tagRepository.findByName(tag.getName());
                    return optionalTag.orElse(new Tag(tag.getName()));
                })
                .collect(Collectors.toSet()));
        return entity;
    }

    @Override
    public ProductDTO toDTO(Product entity) {
        ProductDTO dto = mapper.map(entity, ProductDTO.class);
        dto.setTags(entity.getTags().stream()
                .map(Tag::getName)
                .map(TagDTO::new)
                .collect(Collectors.toSet()));
        return dto;
    }
}
