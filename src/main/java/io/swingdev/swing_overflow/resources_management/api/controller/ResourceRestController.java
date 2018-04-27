package io.swingdev.swing_overflow.resources_management.api.controller;

import io.swingdev.swing_overflow.resources_management.api.dto.ResourceDTO;
import io.swingdev.swing_overflow.resources_management.domain.services.ResourceService;
import org.dozer.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/resources")
public class ResourceRestController {
    private ResourceService resourceService;
    private Mapper mapper;

    public ResourceRestController(ResourceService resourceService, Mapper mapper) {
        this.resourceService = resourceService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ResourceDTO> getResources() {
        return resourceService
            .listAllResources()
            .stream()
            .map(resource -> mapper.map(resource, ResourceDTO.class))
            .collect(Collectors.toList());
    }
}
