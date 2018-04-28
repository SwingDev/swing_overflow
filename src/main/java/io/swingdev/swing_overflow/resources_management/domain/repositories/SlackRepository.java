package io.swingdev.swing_overflow.resources_management.domain.repositories;

import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.user_management.api.dto.UserDTO;
import io.swingdev.swing_overflow.user_management.domain.User;
import io.swingdev.swing_overflow.user_management.domain.UserDetails;

import java.util.List;

public interface SlackRepository {
    List<Message> getHistory(String channelName);
    List<UserDetails> getUsers();
}
