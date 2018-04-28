package io.swingdev.swing_overflow.resources_management.fixtures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.swingdev.swing_overflow.resources_management.domain.Reaction;
import io.swingdev.swing_overflow.resources_management.domain.Resource;
import io.swingdev.swing_overflow.resources_management.domain.repositories.ReactionRepository;
import io.swingdev.swing_overflow.resources_management.domain.repositories.ResourceRepository;
import io.swingdev.swing_overflow.user_management.api.domain.User;
import io.swingdev.swing_overflow.user_management.api.domain.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
public class ReactionsBootstrap implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private ResourceRepository resourceRepository;
    private UserRepository userRepository;
    private ReactionRepository reactionRepository;

    public List<Reaction> reactions;

    public ReactionsBootstrap(ResourceRepository resourceRepository, UserRepository userRepository, ReactionRepository reactionRepository) {
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
        this.reactionRepository = reactionRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        List<Resource> resources = Lists.newArrayList(resourceRepository.findAll());

        List<Reaction> reactions = resources.get(0).getReactions();

        if (reactions == null || reactions.size() == 0) {
            List<User> users = Lists.newArrayList(userRepository.findAll());
            Reaction reaction1 = new Reaction("love", users.get(0), resources.get(0));
            Reaction reaction2 = new Reaction("hate", users.get(1), resources.get(1));
            Reaction reaction3 = new Reaction("like", users.get(2), resources.get(0));

            reactionRepository.saveAll(ImmutableList.of(reaction1, reaction2, reaction3));
        }
    }

    @Override
    public int getOrder() {
        return 50;
    }
}
