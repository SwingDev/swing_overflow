package io.swingdev.swing_overflow.resources_management.domain.services;

import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.Resource;
import io.swingdev.swing_overflow.resources_management.domain.Tag;

import java.util.List;

public interface ResourceService {
    List<Resource> listAllResources();
    List<Resource> findByTags(List<Tag> tags);
    Resource createResourceByMessage(Message message);
    void addResourceCreationStrategy(ResourceCreationStrategy resourceCreationStrategy);
    void save(Resource resource);
}
