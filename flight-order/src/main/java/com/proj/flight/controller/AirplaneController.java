package com.proj.flight.controller;

import com.proj.flight.dto.AirplaneDTO;
import com.proj.flight.dto.AirportDTO;
import com.proj.flight.service.AirplaneService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/airplane")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirplaneController {

    AirplaneService airplaneService;

    @PostMapping("/create")
    public void create(@RequestBody @Valid AirplaneDTO dto) {
        airplaneService.create(dto);
    }

    @GetMapping("/getAll")
    public List<AirplaneDTO> getAllAirplane() {
        return airplaneService.findAll();
    }

    @GetMapping("/getAllNoBusy")
    public Set<AirplaneDTO> getAllAirplaneNoBusy() {
        return new HashSet<>(airplaneService.findAllNoBusy());
    }

    @GetMapping("/get/{iataCode}")
    public AirplaneDTO getAirplane(@PathVariable String iataCode) throws Exception {
        return airplaneService.findByCode(iataCode);
    }

}
