package io.swingdev.swing_overflow.resources_management.fixtures;

import com.google.common.collect.ImmutableList;
import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.repositories.MessageRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Profile("dev")
public class MessagesBootstrap implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private MessageRepository messageRepository;

    public List<Message> messages;

    public MessagesBootstrap(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public int getOrder() {
        return 20;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        if (messageRepository.count() == 0) {
            Message message1 = new Message("Hi, Jo!", new Date());
            Message message2 = new Message("Hi, it's a #resource", new Date());
            Message message3 = new Message("Hi, it's a #resource about #programming.", new Date());
            messages = ImmutableList.of(message1, message2, message3);
            messageRepository.saveAll(messages);
        }

    }
}
