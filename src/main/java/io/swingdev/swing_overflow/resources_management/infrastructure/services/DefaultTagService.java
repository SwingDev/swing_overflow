package io.swingdev.swing_overflow.resources_management.infrastructure.services;

import com.google.common.collect.ImmutableList;
import io.swingdev.swing_overflow.resources_management.domain.Tag;
import io.swingdev.swing_overflow.resources_management.domain.repositories.TagRepository;
import io.swingdev.swing_overflow.resources_management.domain.services.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultTagService implements TagService {
    private TagRepository tagRepository;

    public DefaultTagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> listAllTags() {
        return ImmutableList.copyOf(tagRepository.findAll());
    }

    @Override
    public Tag findByName(String name) {
        return tagRepository.findByName(name);
    }
}
