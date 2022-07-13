package org.aviation.projects.flightcatering.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.flightcatering.dto.ProductDTO;
import org.aviation.projects.flightcatering.service.ProductService;
import org.jfree.util.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(value = "Controller for operation with products")
public class ProductController {

    ProductService productService;

    @GetMapping("/{sortBy}/{order}/{page}/{direction}")
    @ApiOperation(value = "Show all products with pagination", response = Iterable.class)
    public Page<ProductDTO> getAll(@PathVariable Integer order,
                                   @PathVariable Integer page,
                                   @PathVariable String sortBy,
                                   @PathVariable String direction) {
        Pageable request = PageRequest.of(page, order, Sort.by(Sort.Direction.fromString(direction), sortBy));
        Log.info("getAll method called in ProductController");
        return productService.findAll(request);
    }

    @GetMapping
    @ApiOperation(value = "Show all product without pagination. For example when you want to add product to catering order", response = Iterable.class)
    public List<ProductDTO> getAll() {
        Log.info("getAll method called in ProductController");
        return productService.findAll();
    }

    @PostMapping
    @ApiOperation(value = "Create new product")
    public void create(@RequestBody @Valid ProductDTO dto) throws Exception {
        Log.info("create method called in ProductController");
        productService.create(dto);
    }

}
