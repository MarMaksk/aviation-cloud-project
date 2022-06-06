package com.proj.flight.controller;

import com.proj.flight.dto.ExaminationDTO;
import com.proj.flight.entity.Examination;
import com.proj.flight.service.ExaminationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/examination")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExaminationController {

    ExaminationService examinationService;

    @GetMapping("/getAll")
    public List<ExaminationDTO> getAll() {
        return examinationService.findAll();
    }

    @PostMapping("/create")
    public void create(@RequestBody @Valid ExaminationDTO dto) throws Exception {
        examinationService.create(dto);
    }
}
