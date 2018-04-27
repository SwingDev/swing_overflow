package io.swingdev.swing_overflow.resources_management.infrastructure.mappers;

import io.swingdev.swing_overflow.resources_management.api.dto.ResourceDTO;
import io.swingdev.swing_overflow.resources_management.domain.Resource;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper {
    private TypeMap<Resource, ResourceDTO> resourceToDtoMap;

    public ResourceMapper(ModelMapper modelMapper) {
        resourceToDtoMap = modelMapper.createTypeMap(Resource.class, ResourceDTO.class);
        resourceToDtoMap.addMapping(src -> src.getMessage().getText(), ResourceDTO::setOriginalMessage);
    }

    public ResourceDTO toDto(Resource resource) {
        return resourceToDtoMap.map(resource);
    }
}
