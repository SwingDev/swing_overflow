package io.swingdev.swing_overflow.resources_management.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Resource {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "HAS", direction = Relationship.UNDIRECTED)
    private Message message;

    private Resource () { }

    public Resource(Message message) {
        this.message = message;
    }
}
