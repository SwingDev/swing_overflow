package io.swingdev.swing_overflow.user_management.infrastructure.services;

import io.swingdev.swing_overflow.user_management.domain.User;
import io.swingdev.swing_overflow.user_management.domain.repositories.UserRepository;
import io.swingdev.swing_overflow.user_management.domain.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    private UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByExternalId(String externalId) {
        return userRepository.findByExternalId(externalId);
    }

    @Override
    public User ensureByExternalId(String externalId) {
        User user = findByExternalId(externalId);

        if (user != null) {
            return user;
        }

        //TODO name to be migrated
        user = new User(externalId, null);
        userRepository.save(user);

        return user;
    }
}
