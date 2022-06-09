package com.proj.flight.controller;

import com.proj.flight.dto.FlightDTO;
import com.proj.flight.exception.NoSuchAirplaneException;
import com.proj.flight.exception.NoSuchAirportException;
import com.proj.flight.exception.NoSuchFlightException;
import com.proj.flight.service.FlightService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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

    @PostMapping("/create")
    public void create(@Valid @RequestBody FlightDTO dto) throws NoSuchAirplaneException, NoSuchAirportException {
        flightService.create(dto);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody FlightDTO dto) throws NoSuchAirplaneException, NoSuchAirportException, NoSuchFlightException {
        flightService.update(dto);
    }

    @GetMapping("/getAll")
    public List<FlightDTO> getAllFlights() {
        return flightService.findAll();
    }

    @GetMapping("/get/{flightNumber}")
    public FlightDTO getFlight(@PathVariable String flightNumber) throws NoSuchFlightException {
        return flightService.findByCode(flightNumber);
    }
}
