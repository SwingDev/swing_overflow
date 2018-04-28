package io.swingdev.swing_overflow.resources_management.fixtures;

import com.google.common.collect.ImmutableList;
import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.Resource;
import io.swingdev.swing_overflow.resources_management.domain.Tag;
import io.swingdev.swing_overflow.resources_management.domain.repositories.MessageRepository;
import io.swingdev.swing_overflow.resources_management.domain.repositories.ResourceRepository;
import io.swingdev.swing_overflow.resources_management.domain.repositories.TagRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Profile("dev")
public class TagsBootstrap implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private TagRepository tagRepository;

    public List<Tag> tags;

    public TagsBootstrap(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public int getOrder() {
        return 10;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        if (tagRepository.count() == 0) {
            Tag programming = new Tag("programming");
            Tag challenge = new Tag("challenge");
            Tag general = new Tag("general");
            tags = ImmutableList.of(programming, challenge, general);
            tagRepository.saveAll(tags);
        }

    }
}
