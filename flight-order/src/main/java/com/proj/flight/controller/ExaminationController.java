package com.proj.flight.controller;

import com.proj.flight.dto.AirplaneDTO;
import com.proj.flight.dto.ExaminationDTO;
import com.proj.flight.entity.Examination;
import com.proj.flight.service.ExaminationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/examination")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExaminationController {

    ExaminationService examinationService;

    @GetMapping("/{sortBy}/{order}/{page}/{direction}")
    public Page<ExaminationDTO> getAll(@PathVariable Integer order,
                                           @PathVariable Integer page,
                                           @PathVariable String sortBy,
                                           @PathVariable String direction) {
        Pageable request = PageRequest.of(page, order, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return examinationService.findAll(request);
    }

    @PostMapping
    public void create(@RequestBody @Valid ExaminationDTO dto) throws Exception {
        examinationService.create(dto);
    }
}
