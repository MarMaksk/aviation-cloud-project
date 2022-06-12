package com.proj.demo.controller;

import com.proj.demo.dto.OrderDTO;
import com.proj.demo.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderController {

    OrderService orderService;

    @GetMapping("/getAll/{sortBy}/{order}/{page}/{direction}")
    public Page<OrderDTO> getAll(@PathVariable Integer order,
                                 @PathVariable Integer page,
                                 @PathVariable String sortBy,
                                 @PathVariable String direction) {
        Pageable request = PageRequest.of(page, order, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return orderService.findAll(request);
    }

    public void updateOrder(@RequestBody @Valid OrderDTO orderDTO) throws Exception {
        orderService.update(orderDTO);
    }

}
