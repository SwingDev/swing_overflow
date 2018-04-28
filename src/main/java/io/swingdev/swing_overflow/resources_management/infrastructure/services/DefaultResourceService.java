package io.swingdev.swing_overflow.resources_management.infrastructure.services;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.Resource;
import io.swingdev.swing_overflow.resources_management.domain.Tag;
import io.swingdev.swing_overflow.resources_management.domain.repositories.ResourceRepository;
import io.swingdev.swing_overflow.resources_management.domain.services.ResourceCreationStrategy;
import io.swingdev.swing_overflow.resources_management.domain.services.ResourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DefaultResourceService implements ResourceService {
    private ResourceRepository resourceRepository;
    private List<ResourceCreationStrategy> resourceCreationStrategies;

    public DefaultResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
        this.resourceCreationStrategies = new ArrayList<ResourceCreationStrategy>();
    }

    @Override
    public void addResourceCreationStrategy(ResourceCreationStrategy resourceCreationStrategy) {
        resourceCreationStrategies.add(resourceCreationStrategy);
    }

    @Override
    public List<Resource> listAllResources() {
        return ImmutableList.copyOf(resourceRepository.findAll());
    }

    @Override
    public List<Resource> findByTags(List<Tag> tags) {
        return Lists.newArrayList(resourceRepository.findByTagsIn(tags));
    }

    @Override
    public Resource createResourceByMessage(Message message) {
        Resource resource = null;

        resourceCreationStrategies.sort(Comparator.comparingInt(ResourceCreationStrategy::getOrder));

        for (ResourceCreationStrategy resourceCreationStrategy : resourceCreationStrategies) {
            if (resourceCreationStrategy.condition(message)) {
                resource = resourceCreationStrategy.produceResource(message);
                break;
            }
        }

        return resource;
    }

    @Override
    public void save(Resource resource) {
        resourceRepository.save(resource);
    }
}
