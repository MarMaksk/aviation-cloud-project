package org.aviation.projects.flightorder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.flightorder.dto.AirportDTO;
import org.aviation.projects.flightorder.exception.NoSuchAirportException;
import org.aviation.projects.flightorder.service.AirportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(value = "Controller for airports")
public class AirportController {

    static Logger LOG = LoggerFactory.getLogger(AirportController.class);
    AirportService airportService;

    @GetMapping("/{sortBy}/{order}/{page}/{direction}")
    @ApiOperation(value = "Show all airports with pagination", response = Iterable.class)
    public Page<AirportDTO> getAllAirport(@PathVariable Integer order,
                                          @PathVariable Integer page,
                                          @PathVariable String sortBy,
                                          @PathVariable String direction) {
        LOG.info("Get all airports");
        Pageable request = PageRequest.of(page, order, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return airportService.findAll(request);
    }

    @GetMapping
    @ApiOperation(value = "Show all airports without pagination", response = Iterable.class)
    public List<AirportDTO> getAll() {
        LOG.info("Get all airports");
        Pageable pageable = null;
        return airportService.findAll(pageable).stream().collect(Collectors.toList());
    }

    @PostMapping
    @ApiOperation(value = "Add new airport")
    public void create(@RequestBody @Valid AirportDTO dto) {
        LOG.info("Add new airport");
        airportService.create(dto);
    }

    @GetMapping("/{icaoCode}")
    @ApiOperation(value = "Show current airport", response = AirportDTO.class)
    public AirportDTO getAirport(@PathVariable String icaoCode) throws NoSuchAirportException {
        LOG.info("Get airport");
        return airportService.findByCode(icaoCode);
    }

}
