package io.swingdev.swing_overflow.resources_management.infrastructure.repositories;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.swingdev.swing_overflow.resources_management.api.dto.HistoryDTO;
import io.swingdev.swing_overflow.resources_management.api.dto.UserListDTO;
import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.repositories.SlackRepository;
import io.swingdev.swing_overflow.user_management.api.dto.UserDTO;
import io.swingdev.swing_overflow.user_management.domain.UserDetails;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RESTSlackRepository implements SlackRepository {

    private RestTemplate restTemplate;
    private Mapper mapper;
    private String slackToken;

    public RESTSlackRepository(@Value("${slackBotToken}") String slackToken, Mapper mapper) {
        this.mapper = mapper;
        this.slackToken = slackToken;
        //TODO throw not needed stuff, simplify it
        String slackUrl = "https://slack.com";
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory(slackUrl);
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplateBuilder.uriTemplateHandler(defaultUriBuilderFactory);
        restTemplateBuilder.rootUri(slackUrl + "/api");
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
            return ImmutableList.of();
        }

        return historyDTO
                .getMessages()
                .stream()
                .map(dto -> mapper.map(dto, Message.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDetails> getUsers() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://slack.com/api/users.list")
                .queryParam("token", slackToken);
        UserListDTO userListDTO = restTemplate.getForObject(
            builder.toUriString(),
            UserListDTO.class
        );

        if (userListDTO == null) {
            return ImmutableList.of();
        }

        return userListDTO
                .getMembers()
                .stream()
                .map(dto -> mapper.map(dto, UserDetails.class))
                .collect(Collectors.toList());
    }
}
