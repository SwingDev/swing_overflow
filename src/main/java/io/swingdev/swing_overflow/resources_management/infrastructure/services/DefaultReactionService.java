package io.swingdev.swing_overflow.resources_management.infrastructure.services;

import io.swingdev.swing_overflow.resources_management.domain.Reaction;
import io.swingdev.swing_overflow.resources_management.domain.repositories.ReactionRepository;
import io.swingdev.swing_overflow.resources_management.domain.services.ReactionService;
import org.springframework.stereotype.Service;

@Service
public class DefaultReactionService implements ReactionService {
    private ReactionRepository reactionRepository;

    public DefaultReactionService(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    @Override
    public void save(Reaction reaction) {
        reactionRepository.save(reaction);
    }
}
