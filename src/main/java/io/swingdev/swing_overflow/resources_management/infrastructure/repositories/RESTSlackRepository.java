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
    private UriComponentsBuilder uriComponentsBuilder;
    private Mapper mapper;
    private String slackToken;

    public RESTSlackRepository(@Value("${slackBotToken}") String slackToken, Mapper mapper) {
        this.mapper = mapper;
        this.slackToken = slackToken;
        restTemplate = new RestTemplate();
        uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("https://slack.com/api/");
        uriComponentsBuilder.queryParam("token", slackToken);
    }

    @Override
    public List<Message> getHistory(String channelName) {
        return null;
    }

    @Override
    public List<UserDetails> getUsers() {
        UserListDTO userListDTO = restTemplate.getForObject(
            uriComponentsBuilder
                .cloneBuilder()
                .pathSegment("users.list")
                .toUriString(),
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
