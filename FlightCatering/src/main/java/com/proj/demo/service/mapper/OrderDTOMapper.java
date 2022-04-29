package com.proj.demo.service.mapper;

import com.proj.demo.dto.OrderDTO;
import com.proj.demo.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDTOMapper implements EntityToDTOMapper<Order, OrderDTO> {
    @Autowired
    private ModelMapper mapper;

    @Override
    public Order toEntity(OrderDTO dto) {
        return mapper.map(dto, Order.class);
    }

    @Override
    public OrderDTO toDTO(Order entity) {
        return mapper.map(entity, OrderDTO.class);
    }
}
