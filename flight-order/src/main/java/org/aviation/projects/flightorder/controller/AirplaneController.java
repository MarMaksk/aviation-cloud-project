package org.aviation.projects.flightorder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.flightorder.dto.AirplaneDTO;
import org.aviation.projects.flightorder.service.AirplaneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(value = "Controller for airplanes")
public class AirplaneController {

    AirplaneService airplaneService;

    static Logger LOG = LoggerFactory.getLogger(AirplaneController.class);

    @PostMapping
    @ApiOperation(value = "Add new airplane")
    public void create(@RequestBody @Valid AirplaneDTO dto) {
        airplaneService.create(dto);
    }

    @GetMapping("/{sortBy}/{order}/{page}/{direction}")
    @ApiOperation(value = "Show all airplanes with pagination", response = Iterable.class)
    public Page<AirplaneDTO> getAll(@PathVariable Integer order,
                                    @PathVariable Integer page,
                                    @PathVariable String sortBy,
                                    @PathVariable String direction) {
        LOG.info("Get all airplanes with pagination");
        Pageable request = PageRequest.of(page, order, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return airplaneService.findAll(request);
    }

    @GetMapping
    @ApiOperation(value = "Show all no busy airplanes", response = Iterable.class)
    public Set<AirplaneDTO> getAllAirplaneNoBusy() {
        LOG.info("Get all no busy airplanes");
        return new HashSet<>(airplaneService.findAllNoBusy());
    }

    @GetMapping("/{icaoCode}")
    @ApiOperation(value = "Get current airplane", response = AirplaneDTO.class)
    public AirplaneDTO getAirplane(@PathVariable String icaoCode) throws Exception {
        LOG.info("Get airplane by icaoCode");
        return airplaneService.findByCode(icaoCode);
    }

}
