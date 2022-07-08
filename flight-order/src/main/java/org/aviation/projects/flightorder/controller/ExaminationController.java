package org.aviation.projects.flightorder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aviation.projects.flightorder.dto.ExaminationDTO;
import org.aviation.projects.flightorder.service.ExaminationService;
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
@RequestMapping("/examination")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(value = "Controller for examination")
public class ExaminationController {

    ExaminationService examinationService;

    @GetMapping("/{sortBy}/{order}/{page}/{direction}")
    @ApiOperation(value = "Show all examinations with pagination", response = Iterable.class)
    public Page<ExaminationDTO> getAll(@PathVariable Integer order,
                                           @PathVariable Integer page,
                                           @PathVariable String sortBy,
                                           @PathVariable String direction) {
        Pageable request = PageRequest.of(page, order, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return examinationService.findAll(request);
    }

    @PostMapping
    @ApiOperation(value = "Add new examination")
    public void create(@RequestBody @Valid ExaminationDTO dto) throws Exception {
        examinationService.create(dto);
    }
}
