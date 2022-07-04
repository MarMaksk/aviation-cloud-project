package org.aviation.projects.flightorder.controller;

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
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FlightController {

    FlightService flightService;

    @PostMapping
    public void create(@Valid @RequestBody FlightDTO dto, @RequestHeader("Authorization") String token)
            throws NoSuchAirplaneException, NoSuchAirportException, NoSuchFlightException {
        flightService.create(dto, token);
    }

    @PutMapping
    public void update(@Valid @RequestBody FlightDTO dto) throws NoSuchAirplaneException, NoSuchAirportException, NoSuchFlightException {
        flightService.update(dto);
    }

    @DeleteMapping("/{flightNumber}")
    public void delete(@PathVariable String flightNumber) throws NoSuchFlightException {
        flightService.delete(flightNumber);
    }

    @GetMapping
    public List<FlightDTO> getAllFlights() {
        return flightService.findAll();
    }

    @GetMapping("/{flightNumber}")
    public FlightDTO getFlight(@PathVariable String flightNumber) throws NoSuchFlightException {
        return flightService.findByCode(flightNumber);
    }

    @GetMapping("/alternativeFlights/{flightNumber}")
    public List<FlightDTO> findAlternativeFlight(@PathVariable String flightNumber) throws NoSuchFlightException {
        return flightService.findAlternativeFlights(flightNumber);
    }

    @GetMapping("/alternativeFlight/{flightNumber}/{flightNumberAlternative}")
    public void selectAlternativeFlight(@PathVariable String flightNumber, @PathVariable String flightNumberAlternative) throws NoSuchFlightException {
        flightService.selectAlternativeFlight(flightNumber, flightNumberAlternative);
    }

}
