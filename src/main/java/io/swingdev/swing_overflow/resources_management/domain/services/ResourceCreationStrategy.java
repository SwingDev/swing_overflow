package io.swingdev.swing_overflow.resources_management.domain.services;

import io.swingdev.swing_overflow.resources_management.domain.Message;
import io.swingdev.swing_overflow.resources_management.domain.Resource;

public interface ResourceCreationStrategy {
    boolean condition(Message message);
    Resource produceResource(Message message);
    int getOrder();
}
