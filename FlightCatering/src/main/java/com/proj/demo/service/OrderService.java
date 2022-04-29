package com.proj.demo.service;

import com.proj.demo.service.mapper.OrderDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderDTOMapper orderMapper;
}
