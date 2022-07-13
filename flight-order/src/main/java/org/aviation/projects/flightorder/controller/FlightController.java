package org.aviation.projects.flightorder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.flightorder.dto.FlightDTO;
import org.aviation.projects.flightorder.exception.NoSuchAirplaneException;
import org.aviation.projects.flightorder.exception.NoSuchAirportException;
import org.aviation.projects.flightorder.exception.NoSuchFlightException;
import org.aviation.projects.flightorder.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(value = "Controller for flight order")
public class FlightController {

    static Logger LOG = LoggerFactory.getLogger(FlightController.class);
    FlightService flightService;

    @PostMapping
    @ApiOperation(value = "Add new flight order")
    public void create(@Valid @RequestBody FlightDTO dto, @RequestHeader("Authorization") String token)
            throws NoSuchAirplaneException, NoSuchAirportException, NoSuchFlightException {
        LOG.info("Add new flight order");
        flightService.create(dto, token);
    }

    @PutMapping
    @ApiOperation(value = "Update flight order")
    public void update(@Valid @RequestBody FlightDTO dto) throws NoSuchAirplaneException, NoSuchAirportException, NoSuchFlightException {
        LOG.info("Update flight order");
        flightService.update(dto);
    }

    @DeleteMapping("/{flightNumber}")
    @ApiOperation(value = "Delete flight order")
    public void delete(@PathVariable String flightNumber) throws NoSuchFlightException {
        LOG.info("Delete flight order");
        flightService.delete(flightNumber);
    }

    @GetMapping
    @ApiOperation(value = "Show all flights", response = Iterable.class)
    public List<FlightDTO> getAllFlights() {
        LOG.info("Show all flights");
        return flightService.findAll();
    }

    @GetMapping("/{flightNumber}")
    @ApiOperation(value = "Show current flight", response = FlightDTO.class)
    public FlightDTO getFlight(@PathVariable String flightNumber) throws NoSuchFlightException {
        LOG.info("Show current flight");
        return flightService.findByCode(flightNumber);
    }

    @GetMapping("/alternativeFlights/{flightNumber}")
    @ApiOperation(value = "Show alternative flights", response = Iterable.class)
    public List<FlightDTO> findAlternativeFlight(@PathVariable String flightNumber) throws NoSuchFlightException {
        LOG.info("Show alternative flights");
        return flightService.findAlternativeFlights(flightNumber);
    }

    @GetMapping("/alternativeFlight/{flightNumber}/{flightNumberAlternative}")
    @ApiOperation(value = "Select alt flight")
    public void selectAlternativeFlight(@PathVariable String flightNumber, @PathVariable String flightNumberAlternative) throws NoSuchFlightException {
        LOG.info("Select alt flight");
        flightService.selectAlternativeFlight(flightNumber, flightNumberAlternative);
    }

}
