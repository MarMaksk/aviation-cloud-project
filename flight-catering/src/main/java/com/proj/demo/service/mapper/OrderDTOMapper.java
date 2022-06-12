package com.proj.demo.service.mapper;

import com.proj.demo.dto.OrderDTO;
import com.proj.demo.dto.ProductDTO;
import com.proj.demo.entity.Order;
import com.proj.demo.entity.Product;
import com.proj.demo.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.entity.enums.DeliveryStatus;
import org.aviation.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderDTOMapper implements EntityToDTOMapper<Order, OrderDTO> {

    ModelMapper mapper;
    ProductRepository productRepository;
    ProductDTOMapper productDTOMapper;

    @Override
    public Order toEntity(OrderDTO dto) {
        Order map = mapper.map(dto, Order.class);
        Set<Product> allByProductOrderId = productRepository.findAllByProductOrderId(map.getProductOrderId());
        map.setProducts(allByProductOrderId);
        if (dto.getStatus() != null)
            map.setStatus(DeliveryStatus.fromString(dto.getStatus()));
        return map;
    }

    @Override
    public OrderDTO toDTO(Order entity) {
        OrderDTO map = mapper.map(entity, OrderDTO.class);
        List<ProductDTO> productDTOS = productDTOMapper.toDTOs(new ArrayList<>(entity.getProducts()));
        map.setProducts(new HashSet<>(productDTOS));
        return map;
    }
}
