package io.swingdev.swing_overflow.resources_management.fixtures;

import com.google.common.collect.ImmutableList;
import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.repositories.MessageRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            Message message1 = new Message("Hi, Jo!", produceTimestamp("27/04/2018"));
            Message message2 = new Message("Hi, it's a #resource", produceTimestamp("26/04/2018"));
            Message message3 = new Message("Hi, it's a #resource about #programming.", produceTimestamp("25/04/2018"));
            messages = ImmutableList.of(message1, message2, message3);
            messageRepository.saveAll(messages);
        }

    }

    private String produceTimestamp(String dateAsText) {
        String timestamp = null;

        if (dateAsText == null) {
            return null;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(dateAsText);
            long time = date.getTime();
            timestamp = Long.toString(time);
        } catch (ParseException e) {
            return null;
        }

        return timestamp;
    }
}
