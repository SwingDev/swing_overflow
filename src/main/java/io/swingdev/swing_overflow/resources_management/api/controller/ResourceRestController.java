package io.swingdev.swing_overflow.resources_management.api.controller;

import io.swingdev.swing_overflow.resources_management.api.dto.ResourceDTO;
import io.swingdev.swing_overflow.resources_management.domain.services.ResourceService;
import io.swingdev.swing_overflow.resources_management.infrastructure.mappers.ResourceMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/resources")
public class ResourceRestController {
    private ResourceService resourceService;
    private ResourceMapper resourceMapper;

    public ResourceRestController(ResourceService resourceService, ResourceMapper resourceMapper) {
        this.resourceService = resourceService;
        this.resourceMapper = resourceMapper;
    }

    @GetMapping
    public List<ResourceDTO> getResources() {
        return resourceService
            .listAllResources()
            .stream()
            .map(resourceMapper::toDto)
            .collect(Collectors.toList());
    }

}
