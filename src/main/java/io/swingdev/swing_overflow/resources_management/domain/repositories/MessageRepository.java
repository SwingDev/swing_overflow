package io.swingdev.swing_overflow.resources_management.domain.repositories;

import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.Resource;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    Message findByTimestamp(String timestamp);
}
