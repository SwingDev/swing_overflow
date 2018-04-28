package io.swingdev.swing_overflow.resources_management.domain.repositories;

import io.swingdev.swing_overflow.resources_management.domain.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Long> {
    Tag findByName(String name);
    List<Tag> findTagsByNameIn(List<String> name);
}
