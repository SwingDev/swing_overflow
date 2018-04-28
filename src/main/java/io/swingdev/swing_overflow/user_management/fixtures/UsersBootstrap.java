package io.swingdev.swing_overflow.user_management.fixtures;

import com.google.common.collect.ImmutableList;
import io.swingdev.swing_overflow.user_management.domain.User;
import io.swingdev.swing_overflow.user_management.domain.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
public class UsersBootstrap implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private UserRepository userRepository;

    public List<User> users;

    public UsersBootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int getOrder() {
        return 40;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        if (userRepository.count() == 0) {
            User dawid = new User("dawiddominiak");
            User tomek = new User("tomek");
            User kasia = new User("kasia");
            users = ImmutableList.of(dawid, tomek, kasia);
            userRepository.saveAll(users);
        }
    }
}

