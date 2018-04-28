package io.swingdev.swing_overflow.resources_management.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;

@NodeEntity
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    private String text;
    private Date date;

    @Relationship(type = "HAS", direction = Relationship.UNDIRECTED)
    private Resource resource;

    private Message() { }

    public Message(String text, Date date) {
        this.text = text;
        this.date = date;
    }

    public Message(String text, Date date, Resource resource) {
        this.text = text;
        this.date = date;
        this.resource = resource;
    }

    public String getText() {
        return text;
    }

    public void pinResource(Resource resource) {
        this.resource = resource;
    }
}
