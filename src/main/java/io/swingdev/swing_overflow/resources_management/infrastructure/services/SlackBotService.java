package io.swingdev.swing_overflow.resources_management.infrastructure.services;

import io.swingdev.swing_overflow.resources_management.api.dto.MessageDTO;
import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.Resource;
import io.swingdev.swing_overflow.resources_management.domain.services.ResourceService;
import me.ramswaroop.jbot.core.common.Controller;
import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import me.ramswaroop.jbot.core.slack.Bot;
import org.springframework.web.socket.WebSocketSession;

import java.util.Date;

@Component
public class SlackBotService extends Bot {

    private String slackToken;
    private Mapper mapper;
    private ResourceService resourceService;

    public SlackBotService(@Value("${slackBotToken}") String slackToken, Mapper mapper, ResourceService resourceService) {
        this.slackToken = slackToken;
        this.mapper = mapper;
        this.resourceService = resourceService;
    }

    @Override
    public String getSlackToken() {
        return slackToken;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }

    @Controller(events = {EventType.MESSAGE, EventType.DIRECT_MESSAGE}, pattern = "#resource")
    public void onReceiveResource(WebSocketSession session, Event event) {
        MessageDTO messageDto = mapper.map(event, MessageDTO.class);

        //TODO pin Dozer to Spring Hibernate
        Message message = new Message(
                messageDto.getText(),
                mapper.map(messageDto.getTs(), Date.class)
        );

        Resource resource = resourceService.createResourceByMessage(message);

        resourceService.save(resource);
    }
}

