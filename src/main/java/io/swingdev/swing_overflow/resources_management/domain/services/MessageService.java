package io.swingdev.swing_overflow.resources_management.domain.services;

import io.swingdev.swing_overflow.resources_management.domain.Message;

public interface MessageService {
    Message findByTimestamp(String timestamp);
}
