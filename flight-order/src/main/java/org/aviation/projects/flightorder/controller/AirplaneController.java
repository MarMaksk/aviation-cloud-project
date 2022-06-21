package org.aviation.projects.flightorder.controller;

import org.aviation.projects.flightorder.dto.AirplaneDTO;
import org.aviation.projects.flightorder.service.AirplaneService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/airplane")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirplaneController {

    AirplaneService airplaneService;

    @PostMapping
    public void create(@RequestBody @Valid AirplaneDTO dto) {
        airplaneService.create(dto);
    }

    @GetMapping("/{sortBy}/{order}/{page}/{direction}")
    public Page<AirplaneDTO> getAll(@PathVariable Integer order,
                                          @PathVariable Integer page,
                                          @PathVariable String sortBy,
                                          @PathVariable String direction) {
        Pageable request = PageRequest.of(page, order, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return airplaneService.findAll(request);
    }

    @GetMapping
    public Set<AirplaneDTO> getAllAirplaneNoBusy() {
        return new HashSet<>(airplaneService.findAllNoBusy());
    }

    @GetMapping("/{iataCode}")
    public AirplaneDTO getAirplane(@PathVariable String iataCode) throws Exception {
        return airplaneService.findByCode(iataCode);
    }

}