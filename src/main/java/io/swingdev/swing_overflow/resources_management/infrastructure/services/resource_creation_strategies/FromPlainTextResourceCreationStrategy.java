package io.swingdev.swing_overflow.resources_management.infrastructure.services.resource_creation_strategies;

import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.Resource;
import io.swingdev.swing_overflow.resources_management.domain.Tag;
import io.swingdev.swing_overflow.resources_management.domain.services.ResourceCreationStrategy;
import io.swingdev.swing_overflow.resources_management.domain.services.ResourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FromPlainTextResourceCreationStrategy implements ResourceCreationStrategy {
    public FromPlainTextResourceCreationStrategy(ResourceService resourceService) {
        resourceService.addResourceCreationStrategy(this);
    }

    @Override
    public boolean condition(Message message) {
        return true;
    }

    @Override
    public Resource produceResource(Message message) {
        String text = message.getText().replaceAll("#resource", "").trim();

        return new Resource(message, new ArrayList<Tag>(), text, "to be implemented");
    }

    @Override
    public int getOrder() {
        return 30;
    }
}
