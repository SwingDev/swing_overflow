package io.swingdev.swing_overflow.resources_management.infrastructure.repositories;

import com.google.common.collect.ImmutableMap;
import io.swingdev.swing_overflow.resources_management.api.dto.HistoryDTO;
import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.repositories.SlackRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

@Service
public class RESTSlackRepository implements SlackRepository {
    public static String SLACK_URL = "https://slack.com";

    private RestTemplate restTemplate;
    private String slackToken;

    public RESTSlackRepository(@Value("${slackBotToken}") String slackToken) {
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory(SLACK_URL);
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplateBuilder.uriTemplateHandler(defaultUriBuilderFactory);
        restTemplateBuilder.rootUri("/api");
        restTemplate = restTemplateBuilder.build();
        this.slackToken = slackToken;
    }

    @Override
    public List<Message> getHistory(String channelName) {
        HistoryDTO historyDTO= restTemplate.getForObject(
            "/channels.history",
            HistoryDTO.class,
            ImmutableMap.of(
                "channel", channelName,
                "token", slackToken
            )
        );


        return null;
    }
}
