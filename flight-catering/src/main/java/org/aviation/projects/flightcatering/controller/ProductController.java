package org.aviation.projects.flightcatering.controller;

import org.aviation.projects.flightcatering.dto.ProductDTO;
import org.aviation.projects.flightcatering.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductController {

    ProductService productService;

    @GetMapping("/{sortBy}/{order}/{page}/{direction}")
    public Page<ProductDTO> getAll(@PathVariable Integer order,
                                   @PathVariable Integer page,
                                   @PathVariable String sortBy,
                                   @PathVariable String direction) {
        Pageable request = PageRequest.of(page, order, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return productService.findAll(request);
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.findAll();
    }

    @PostMapping
    public void create(@RequestBody @Valid ProductDTO dto) throws Exception {
        productService.create(dto);
    }

}
