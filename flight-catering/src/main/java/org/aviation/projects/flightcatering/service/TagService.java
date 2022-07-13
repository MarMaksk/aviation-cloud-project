package org.aviation.projects.flightcatering.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.flightcatering.dto.TagDTO;
import org.aviation.projects.flightcatering.entity.Tag;
import org.aviation.projects.flightcatering.repository.TagRepository;
import org.jfree.util.Log;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TagService {

    TagRepository tagRepository;

    public Set<TagDTO> getAll() {
        Log.info("getAll method called in TagService");
        return tagRepository.findAll().stream().map(Tag::getName).map(TagDTO::new).collect(Collectors.toSet());
    }


}
