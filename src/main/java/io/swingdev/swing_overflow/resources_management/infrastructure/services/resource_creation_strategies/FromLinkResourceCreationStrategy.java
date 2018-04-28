package io.swingdev.swing_overflow.resources_management.infrastructure.services.resource_creation_strategies;

import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.Resource;
import io.swingdev.swing_overflow.resources_management.domain.services.ResourceCreationStrategy;
import io.swingdev.swing_overflow.resources_management.domain.services.ResourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FromLinkResourceCreationStrategy implements ResourceCreationStrategy {
    private Pattern regex = Pattern.compile("<(https?://[^\\s]+)>");

    public FromLinkResourceCreationStrategy(ResourceService resourceService) {
        resourceService.addResourceCreationStrategy(this);
    }

    @Override
    public boolean condition(Message message) {
        return getLink(message) != null;
    }

    @Override
    public Resource produceResource(Message message) {
        return new Resource(message, new ArrayList<>(), "to be implemented", getLink(message));
    }

    @Override
    public int getOrder() {
        return 20;
    }

    private String getLink(Message message) {
        Matcher matcher = regex.matcher(message.getText());

        List<String> links = new ArrayList<>();

        while (matcher.find()) {
            links.add(matcher.group().substring(1, matcher.group().length()-1));
        }

        if (links.size() == 0) {
            return null;
        }

        return links.get(0);
    }
}
