package com.proj.demo.service;

import com.proj.demo.dto.OrderDTO;
import com.proj.demo.entity.Order;
import lombok.RequiredArgsConstructor;
import org.aviation.entity.enums.DeliveryStatus;
import com.proj.demo.repository.OrderRepository;
import com.proj.demo.service.mapper.OrderDTOMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.entity.InfoForOrder;
import org.aviation.service.CRUD;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderService implements CRUD<OrderDTO> {

    OrderDTOMapper orderMapper;
    OrderRepository repository;

    public void createByInfo(InfoForOrder info) {
        Order order = new Order();
        order.setIataCode(info.getIataCode());
        order.setIcaoCode(info.getIcaoCode());
        order.setLastDate(info.getDeparture().minusHours(4));
        order.setProductOrderId(info.getProductOrderId());
        order.setStatus(DeliveryStatus.CREATED);
        repository.save(order);
    }

    @Override
    public void create(OrderDTO dto) throws Exception {

    }

    @Override
    public OrderDTO findByCode(String code) throws Exception {
        return null;
    }


    @Override
    public OrderDTO update(OrderDTO dto) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }
}
