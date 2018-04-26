package io.swingdev.swing_overflow.resources_management.api.dto;

import java.util.List;

public class HistoryDTO {
    private List<MessageDTO> messages;

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }
}
