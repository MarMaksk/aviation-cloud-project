package org.aviation.projects.flightorder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aviation.projects.flightorder.dto.FlightDTO;
import org.aviation.projects.flightorder.exception.NoSuchAirplaneException;
import org.aviation.projects.flightorder.exception.NoSuchAirportException;
import org.aviation.projects.flightorder.exception.NoSuchFlightException;
import org.aviation.projects.flightorder.service.FlightService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(value = "Controller for flight order")
public class FlightController {

    FlightService flightService;

    @PostMapping
    @ApiOperation(value = "Add new flight order")
    public void create(@Valid @RequestBody FlightDTO dto, @RequestHeader("Authorization") String token)
            throws NoSuchAirplaneException, NoSuchAirportException, NoSuchFlightException {
        flightService.create(dto, token);
    }

    @PutMapping
    @ApiOperation(value = "Update flight order")
    public void update(@Valid @RequestBody FlightDTO dto) throws NoSuchAirplaneException, NoSuchAirportException, NoSuchFlightException {
        flightService.update(dto);
    }

    @DeleteMapping("/{flightNumber}")
    @ApiOperation(value = "Delete flight order")
    public void delete(@PathVariable String flightNumber) throws NoSuchFlightException {
        flightService.delete(flightNumber);
    }

    @GetMapping
    @ApiOperation(value = "Show all flights", response = Iterable.class)
    public List<FlightDTO> getAllFlights() {
        return flightService.findAll();
    }

    @GetMapping("/{flightNumber}")
    @ApiOperation(value = "Show current flight", response = FlightDTO.class)
    public FlightDTO getFlight(@PathVariable String flightNumber) throws NoSuchFlightException {
        return flightService.findByCode(flightNumber);
    }

    @GetMapping("/alternativeFlights/{flightNumber}")
    @ApiOperation(value = "Show alternative flights", response = Iterable.class)
    public List<FlightDTO> findAlternativeFlight(@PathVariable String flightNumber) throws NoSuchFlightException {
        return flightService.findAlternativeFlights(flightNumber);
    }

    @GetMapping("/alternativeFlight/{flightNumber}/{flightNumberAlternative}")
    @ApiOperation(value = "Select alt flight")
    public void selectAlternativeFlight(@PathVariable String flightNumber, @PathVariable String flightNumberAlternative) throws NoSuchFlightException {
        flightService.selectAlternativeFlight(flightNumber, flightNumberAlternative);
    }

}
