package org.aviation.projects.flightcatering.controller;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aviation.projects.flightcatering.dto.OrderDTO;
import org.aviation.projects.flightcatering.exception.NoSuchOrderException;
import org.aviation.projects.flightcatering.service.OrderService;
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
@Api(value = "Controller for catering order")
public class OrderController {

    OrderService orderService;

    @GetMapping("/{sortBy}/{order}/{page}/{direction}")
    @ApiOperation(value = "show all flight order with pagination", response = Iterable.class)
    public Page<OrderDTO> getAll(@PathVariable Integer order,
                                 @PathVariable Integer page,
                                 @PathVariable String sortBy,
                                 @PathVariable String direction) {
        Pageable request = PageRequest.of(page, order, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return orderService.findAll(request);
    }

    @Timed("getOrder")
    @GetMapping("/{productOrderId}")
    @ApiOperation(value = "show current order", response = OrderDTO.class)
    public OrderDTO getOrder(@PathVariable Integer productOrderId) throws NoSuchOrderException {
        return orderService.findByproductOrderId(productOrderId);
    }

    @PutMapping
    @ApiOperation(value = "update order")
    public void update(@RequestBody @Valid OrderDTO orderDTO) throws Exception {
        System.out.println(orderDTO);
        orderService.update(orderDTO);
    }

    @DeleteMapping("/{productOrderId}")
    @ApiOperation(value = "delete order")
    public void delete(@PathVariable String productOrderId) throws NoSuchOrderException {
        orderService.delete(productOrderId);
    }

}
