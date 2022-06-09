package com.proj.demo.service.mapper;

import com.proj.demo.dto.OrderDTO;
import com.proj.demo.entity.Order;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aviation.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDTOMapper implements EntityToDTOMapper<Order, OrderDTO> {

    private final ModelMapper mapper;

    @Override
    public Order toEntity(OrderDTO dto) {
        return mapper.map(dto, Order.class);
    }

    @Override
    public OrderDTO toDTO(Order entity) {
        return mapper.map(entity, OrderDTO.class);
    }
}
