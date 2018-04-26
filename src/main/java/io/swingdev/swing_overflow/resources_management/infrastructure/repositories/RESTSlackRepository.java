package io.swingdev.swing_overflow.resources_management.infrastructure.repositories;

import com.google.common.collect.ImmutableMap;
import io.swingdev.swing_overflow.resources_management.api.dto.HistoryDTO;
import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.repositories.SlackRepository;
import io.swingdev.swing_overflow.resources_management.infrastructure.mappers.MessageMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RESTSlackRepository implements SlackRepository {
    private static String SLACK_URL = "https://slack.com";

    private RestTemplate restTemplate;
    private MessageMapper messageMapper;

    public RESTSlackRepository(@Value("${slackBotToken}") String slackToken, MessageMapper messageMapper) {
        this.messageMapper = messageMapper;

        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory(SLACK_URL);
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplateBuilder.uriTemplateHandler(defaultUriBuilderFactory);
        restTemplateBuilder.rootUri("/api");
        restTemplate = restTemplateBuilder.build();
        restTemplate.setDefaultUriVariables(ImmutableMap.of("token", slackToken));
    }

    @Override
    public List<Message> getHistory(String channelName) {
        HistoryDTO historyDTO = restTemplate.getForObject(
            "/channels.history",
            HistoryDTO.class,
            ImmutableMap.of("channel", channelName)
        );

        if (historyDTO == null) {
            return new ArrayList<>();
        }

        return historyDTO
                .messages
                .stream()
                .map(messageMapper::toMessage)
                .collect(Collectors.toList());
    }
}
