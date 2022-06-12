package com.proj.demo.service;

import com.proj.demo.dto.OrderDTO;
import com.proj.demo.entity.Order;
import com.proj.demo.exception.NoSuchOrderException;
import com.proj.demo.repository.OrderRepository;
import com.proj.demo.service.mapper.OrderDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.entity.InfoForOrder;
import org.aviation.entity.enums.DeliveryStatus;
import org.aviation.service.CRUD;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderService implements CRUD<OrderDTO> {

    OrderDTOMapper orderMapper;
    OrderRepository repository;
    ModelMapper mapper;

    public Page<OrderDTO> findAll(Pageable pageable) {
        return repository.findAllByDeletedFalse(pageable).map(orderMapper::toDTO);
    }

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
    @Deprecated
    public void create(OrderDTO dto) throws Exception {
        repository.save(orderMapper.toEntity(dto));
    }

    @Override
    public OrderDTO findByCode(String productOrderId) throws Exception {
        return orderMapper.toDTO(repository.findByProductOrderIdAndDeletedFalse(Integer.parseInt(productOrderId)).orElseThrow(NoSuchOrderException::new));
    }


    @Override
    public OrderDTO update(OrderDTO dto) throws Exception {
        Order order = repository.findByProductOrderIdAndDeletedFalse(dto.getProductOrderId()).orElseThrow(NoSuchOrderException::new);
        Order newOrder = orderMapper.toEntity(dto);
        mapper.map(newOrder, order);
        Order save = repository.save(order);
        return orderMapper.toDTO(save);
    }

    @Override
    public void delete(Long productOrderId) throws Exception {
        Order order = repository.findByProductOrderIdAndDeletedFalse(productOrderId.intValue()).orElseThrow(NoSuchOrderException::new);
        order.setDeleted(true);
        repository.save(order);
    }
}
