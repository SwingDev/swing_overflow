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
public class ResourcesBootstrap implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private ResourceRepository resourceRepository;
    private MessagesBootstrap messagesBootstrap;
    private TagsBootstrap tagsBootstrap;

    public List<Resource> resources;

    public ResourcesBootstrap(ResourceRepository resourceRepository, MessagesBootstrap messagesBootstrap, TagsBootstrap tagsBootstrap) {
        this.resourceRepository = resourceRepository;
        this.messagesBootstrap = messagesBootstrap;
        this.tagsBootstrap = tagsBootstrap;
    }

    @Override
    public int getOrder() {
        return 30;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        if (resourceRepository.count() == 0) {
            Resource resource1 = new Resource(
                    messagesBootstrap.messages.get(1),
                    ImmutableList.of(tagsBootstrap.tags.get(2)),
                    "Something about nothing.",
                    "https://medium.com/@the_jennitaur/how-to-do-nothing-57e100f59bbb"
            );
            Resource resource2 = new Resource(
                    messagesBootstrap.messages.get(2),
                    ImmutableList.of(tagsBootstrap.tags.get(0), tagsBootstrap.tags.get(1)),
                    "Magic Land of Programming",
                    "https://medium.com/elixir-magic/tagged/functional-programming");
            resources = ImmutableList.of(resource1, resource2);
            resourceRepository.saveAll(resources);
        }

    }
}
