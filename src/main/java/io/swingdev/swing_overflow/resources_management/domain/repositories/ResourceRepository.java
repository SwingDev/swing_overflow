package io.swingdev.swing_overflow.resources_management.domain.repositories;

import io.swingdev.swing_overflow.resources_management.domain.Resource;
import io.swingdev.swing_overflow.resources_management.domain.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
    Iterable<Resource> findByTagsIn(List<Tag> tags);
}
