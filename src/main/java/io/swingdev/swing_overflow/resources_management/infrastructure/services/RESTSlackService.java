package io.swingdev.swing_overflow.resources_management.infrastructure.services;

import io.swingdev.swing_overflow.resources_management.domain.repositories.SlackRepository;
import io.swingdev.swing_overflow.resources_management.domain.services.SlackService;
import io.swingdev.swing_overflow.user_management.domain.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RESTSlackService implements SlackService {
    private SlackRepository slackRepository;

    public RESTSlackService(SlackRepository slackRepository) {
        this.slackRepository = slackRepository;
    }

    @Override
    public Map<String, String> getExternalIdUsernameMap() {
        List<UserDetails> users = slackRepository.getUsers();

        Map<String, String> externalIdUsernameMap = new HashMap<>();
        users.forEach(userDetails -> externalIdUsernameMap.put(userDetails.getId(), userDetails.getName()));

        return externalIdUsernameMap;
    }
}
