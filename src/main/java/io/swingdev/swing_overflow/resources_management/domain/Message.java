package io.swingdev.swing_overflow.resources_management.domain;

import org.neo4j.ogm.annotation.*;

import java.util.Date;

@NodeEntity
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @Index(unique = true)
    private String timestamp;

    @Relationship(type = "HAS", direction = Relationship.UNDIRECTED)
    private Resource resource;

    private Message() { }

    public Message(String text, String timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void pinResource(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }
}
