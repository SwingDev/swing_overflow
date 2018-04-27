package io.swingdev.swing_overflow.resources_management.infrastructure.services;

import com.google.common.collect.ImmutableList;
import io.swingdev.swing_overflow.resources_management.domain.Resource;
import io.swingdev.swing_overflow.resources_management.domain.repositories.ResourceRepository;
import io.swingdev.swing_overflow.resources_management.domain.services.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultResourceService implements ResourceService {
    private ResourceRepository resourceRepository;

    public DefaultResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public List<Resource> listAllResources() {
        return ImmutableList.copyOf(resourceRepository.findAll());
    }
}