package io.swingdev.swing_overflow.resources_management.domain.repositories;

import io.swingdev.swing_overflow.resources_management.domain.Message;

import java.util.List;

public interface SlackRepository {
    List<Message> getHistory(String channelName);
}
