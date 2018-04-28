package io.swingdev.swing_overflow.resources_management.api.controller;

import io.swingdev.swing_overflow.resources_management.api.dto.TagDTO;
import io.swingdev.swing_overflow.resources_management.domain.services.TagService;
import org.dozer.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tags")
public class TagsRestController {
    private TagService tagService;
    private Mapper mapper;

    public TagsRestController(TagService tagService, Mapper mapper) {
        this.tagService = tagService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<TagDTO> getTags() {
        return tagService
            .listAllTags()
            .stream()
            .map(tag -> mapper.map(tag, TagDTO.class))
            .collect(Collectors.toList());
    }
}
