package io.swingdev.swing_overflow.resources_management.domain.services;

import io.swingdev.swing_overflow.resources_management.domain.Tag;

import java.util.List;

public interface TagService {
    List<Tag> listAllTags();
    Tag findByName(String name);
}
