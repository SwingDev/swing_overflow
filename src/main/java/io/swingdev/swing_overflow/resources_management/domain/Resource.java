package io.swingdev.swing_overflow.resources_management.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class Resource {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "HAS", direction = Relationship.UNDIRECTED)
    private Message message;

    @Relationship(type = "TAGGED_BY")
    private List<Tag> tags;

    @Relationship(type = "REACTED_WITH", direction = Relationship.INCOMING)
    private List<Reaction> reactions;

    private String shortcut;

    private String link;

    private Resource () { }

    public Message getMessage() {
        return message;
    }

    public Resource(Message message, List<Tag> tags, String shortcut, String link) {
        this.message = message;
        this.tags = tags;
        this.shortcut = shortcut;
        this.link = link;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void addReaction(Reaction reaction) {
        reactions.add(reaction);
    }

    public int score() {
        return reactions.size();
    }
}
