package io.swingdev.swing_overflow.resources_management.infrastructure.services;

import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.repositories.MessageRepository;
import io.swingdev.swing_overflow.resources_management.domain.services.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMessageService implements MessageService {
    private MessageRepository messageRepository;

    public DefaultMessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message findByTimestamp(String timestamp) {
        return messageRepository.findByTimestamp(timestamp);
    }
}
