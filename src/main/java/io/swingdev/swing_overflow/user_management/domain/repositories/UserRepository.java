package io.swingdev.swing_overflow.user_management.domain.repositories;

import io.swingdev.swing_overflow.user_management.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByExternalId(String externalId);
}
