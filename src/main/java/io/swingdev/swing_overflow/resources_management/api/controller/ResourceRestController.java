package io.swingdev.swing_overflow.resources_management.api.controller;

import io.swingdev.swing_overflow.resources_management.api.dto.ResourceDTO;
import io.swingdev.swing_overflow.resources_management.domain.Reaction;
import io.swingdev.swing_overflow.resources_management.domain.services.ResourceService;
import io.swingdev.swing_overflow.resources_management.infrastructure.services.RESTSlackService;
import org.dozer.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/resources")
public class ResourceRestController {
    private ResourceService resourceService;
    private Mapper mapper;
    private RESTSlackService restSlackService;

    public ResourceRestController(
        ResourceService resourceService,
        RESTSlackService restSlackService,
        Mapper mapper
    ) {
        this.resourceService = resourceService;
        this.restSlackService = restSlackService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ResourceDTO> getResources(
            @RequestParam(value = "tags[]", required = false) String[] tagNames
    ) {
        //TODO - consider move it somewhere. It's a little too big.
        List<ResourceDTO> resources = resourceService
            .listAllResources()
            .stream()
            .map(resource -> mapper.map(resource, ResourceDTO.class))
            .collect(Collectors.toList());

        Map<String, String> idUsernameMap = restSlackService.getExternalIdUsernameMap();

        resources
            .forEach(dto -> dto.getReactions()
                .forEach(reactionDTO -> {
                    String id = reactionDTO.getUser();
                    if (idUsernameMap.containsKey(id)) {
                        reactionDTO.setUser(idUsernameMap.get(id));
                    }
                })
            );

        return resources;
    }
}
