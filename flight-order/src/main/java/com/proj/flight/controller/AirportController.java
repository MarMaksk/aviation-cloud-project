package com.proj.flight.controller;

import com.proj.flight.dto.AirportDTO;
import com.proj.flight.dto.FlightDTO;
import com.proj.flight.exception.NoSuchAirportException;
import com.proj.flight.exception.NoSuchFlightException;
import com.proj.flight.service.AirportService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/airport")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirportController {

    AirportService airportService;

    @GetMapping("/getAll")
    public List<AirportDTO> getAllAirport() {
        return airportService.findAll();
    }

    @PostMapping("/create")
    public void create(@RequestBody @Valid AirportDTO dto) {
        airportService.create(dto);
    }

    @GetMapping("/get/{icaoCode}")
    public AirportDTO getAirplane(@PathVariable String icaoCode) throws NoSuchAirportException {
        return airportService.findByCode(icaoCode);
    }

}
