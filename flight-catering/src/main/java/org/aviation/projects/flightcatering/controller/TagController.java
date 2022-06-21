package org.aviation.projects.flightcatering.controller;

import org.aviation.projects.flightcatering.dto.TagDTO;
import org.aviation.projects.flightcatering.service.TagService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/tag")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TagController {

    TagService tagService;

    @GetMapping
    private Set<TagDTO> getAll() {
        return tagService.getAll();
    }

}
