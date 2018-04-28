package io.swingdev.swing_overflow.user_management.api.domain;

import io.swingdev.swing_overflow.resources_management.domain.Reaction;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "REACTED_WITH", direction = Relationship.OUTGOING)
    private List<Reaction> reactions;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void addReaction(Reaction reaction) {
        reactions.add(reaction);
    }
}
