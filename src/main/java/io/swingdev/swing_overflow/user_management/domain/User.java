package io.swingdev.swing_overflow.user_management.domain;

import io.swingdev.swing_overflow.resources_management.domain.Reaction;
import org.neo4j.ogm.annotation.*;

import java.util.List;

@NodeEntity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String externalId;

    //TODO remove name - name can be changed in Slack
    private String name;

    @Relationship(type = "REACTED_WITH", direction = Relationship.OUTGOING)
    private List<Reaction> reactions;

    private User() {
    }

    public User(String externalId, String name) {
        this.externalId = externalId;
        this.name = name;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void addReaction(Reaction reaction) {
        reactions.add(reaction);
    }
}
