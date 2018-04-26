package io.swingdev.swing_overflow.resources_management.fixtures;

import com.google.common.collect.ImmutableList;
import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.Resource;
import io.swingdev.swing_overflow.resources_management.domain.repositories.MessageRepository;
import io.swingdev.swing_overflow.resources_management.domain.repositories.ResourceRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private ResourceRepository resourceRepository;
    private MessageRepository messageRepository;

    public DevBootstrap(ResourceRepository resourceRepository, MessageRepository messageRepository) {
        this.resourceRepository = resourceRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        Message message1 = new Message("Hi, Jo!", new Date());
        Message message2 = new Message("Hi, it's a #resource", new Date());
        messageRepository.saveAll(ImmutableList.of(message1, message2));

        Resource resource1 = new Resource(message2);
        resourceRepository.saveAll(ImmutableList.of(resource1));
    }
}
