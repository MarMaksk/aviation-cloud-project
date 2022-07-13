package org.aviation.projects.flightcatering.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.flightcatering.dto.TagDTO;
import org.aviation.projects.flightcatering.service.TagService;
import org.jfree.util.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(value = "Controller for tags")
public class TagController {

    TagService tagService;

    @GetMapping
    @ApiOperation(value = "Show all tags", response = Iterable.class)
    private Set<TagDTO> getAll() {
        Log.info("getAll method called in TagController");
        return tagService.getAll();
    }

}
