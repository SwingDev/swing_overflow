package io.swingdev.swing_overflow.resources_management.domain;

import io.swingdev.swing_overflow.user_management.api.domain.User;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type="REACTED_WITH")
public class Reaction {
    @Id
    @GeneratedValue
    private Long relationshipId;

    @Property
    private String emotion;

    @StartNode
    private User user;

    @EndNode
    private Resource resource;

    public Reaction() {
    }

    public Reaction(String emotion, User user, Resource resource) {
        this.emotion = emotion;
        this.user = user;
        this.resource = resource;
    }
}
