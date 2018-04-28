package io.swingdev.swing_overflow.user_management.domain.services;

import io.swingdev.swing_overflow.user_management.domain.User;

public interface UserService {
    User findByExternalId(String externalId);
    User ensureByExternalId(String externalId);
}
