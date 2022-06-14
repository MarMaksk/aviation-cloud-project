package com.proj.demo.service;

import com.proj.demo.dto.TagDTO;
import com.proj.demo.entity.Product;
import com.proj.demo.entity.Tag;
import com.proj.demo.repository.TagRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TagService {

    TagRepository tagRepository;

    public Set<TagDTO> getAll() {
        return tagRepository.findAll().stream().map(Tag::getName).map(TagDTO::new).collect(Collectors.toSet());
    }


}
