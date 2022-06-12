package com.proj.demo.controller;

import com.proj.demo.dto.OrderDTO;
import com.proj.demo.dto.ProductDTO;
import com.proj.demo.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductController {

    ProductService productService;

    @GetMapping("/getAll/{sortBy}/{order}/{page}/{direction}")
    public Page<ProductDTO> getAll(@PathVariable Integer order,
                                   @PathVariable Integer page,
                                   @PathVariable String sortBy,
                                   @PathVariable String direction) {
        Pageable request = PageRequest.of(page, order, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return productService.findAll(request);
    }

}
