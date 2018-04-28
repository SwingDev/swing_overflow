package io.swingdev.swing_overflow.user_management.api.domain.repositories;

import io.swingdev.swing_overflow.user_management.api.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
