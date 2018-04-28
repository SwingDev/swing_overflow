package io.swingdev.swing_overflow.resources_management.infrastructure.services;

import io.swingdev.swing_overflow.resources_management.api.dto.MessageDTO;
import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.Reaction;
import io.swingdev.swing_overflow.resources_management.domain.Resource;
import io.swingdev.swing_overflow.resources_management.domain.services.MessageService;
import io.swingdev.swing_overflow.resources_management.domain.services.ReactionService;
import io.swingdev.swing_overflow.resources_management.domain.services.ResourceService;
import io.swingdev.swing_overflow.user_management.domain.User;
import io.swingdev.swing_overflow.user_management.domain.services.UserService;
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
    private MessageService messageService;
    private UserService userService;
    private ReactionService reactionService;

    public SlackBotService(
        @Value("${slackBotToken}") String slackToken,
        Mapper mapper,
        ResourceService resourceService,
        MessageService messageService,
        UserService userService,
        ReactionService reactionService
    ) {
        this.slackToken = slackToken;
        this.mapper = mapper;
        this.resourceService = resourceService;
        this.messageService = messageService;
        this.userService = userService;
        this.reactionService = reactionService;
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
                messageDto.getTs()
        );

        Resource resource = resourceService.createResourceByMessage(message);
        resourceService.save(resource);

        reply(session, event, "Resource stored in database under ID " + resource.id());
    }

    @Controller(events = {EventType.REACTION_ADDED})
    public void onReactionAdd(WebSocketSession session, Event event) {
        String timestamp = event.getItem().getTs();

        Message message = messageService.findByTimestamp(timestamp);

        if (message != null) {
            User user = userService.ensureByExternalId(event.getUserId());
            Reaction reaction = new Reaction(event.getReaction(), user, message.getResource());
            reactionService.save(reaction);
        }
    }
}

