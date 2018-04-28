package io.swingdev.swing_overflow.resources_management.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Tag {
    public Tag() {
    }

    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
