package org.aviation.projects.flightorder.controller;

import org.aviation.projects.flightorder.dto.AirportDTO;
import org.aviation.projects.flightorder.exception.NoSuchAirportException;
import org.aviation.projects.flightorder.service.AirportService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/airport")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirportController {

    AirportService airportService;

    @GetMapping("/{sortBy}/{order}/{page}/{direction}")
    public Page<AirportDTO> getAllAirport(@PathVariable Integer order,
                                       @PathVariable Integer page,
                                       @PathVariable String sortBy,
                                       @PathVariable String direction) {
        Pageable request = PageRequest.of(page, order, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return airportService.findAll(request);
    }

    @GetMapping
    public List<AirportDTO> getAll() {
        Pageable pageable = null;
        return airportService.findAll(pageable).stream().collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody @Valid AirportDTO dto) {
        airportService.create(dto);
    }

    @GetMapping("/{icaoCode}")
    public AirportDTO getAirport(@PathVariable String icaoCode) throws NoSuchAirportException {
        return airportService.findByCode(icaoCode);
    }

}
