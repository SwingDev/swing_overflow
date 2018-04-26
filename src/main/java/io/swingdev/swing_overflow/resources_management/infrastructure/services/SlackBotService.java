package io.swingdev.swing_overflow.resources_management.infrastructure.services;

import me.ramswaroop.jbot.core.common.Controller;
import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import me.ramswaroop.jbot.core.slack.Bot;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SlackBotService extends Bot {

    private String slackToken;

    public SlackBotService(@Value("${slackBotToken}") String slackToken) {
        this.slackToken = slackToken;
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
        System.out.print(event.getMessage().getText().toString());
    }
}

