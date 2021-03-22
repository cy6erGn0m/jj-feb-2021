package org.levelp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
public class Storage {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @OneToMany(mappedBy = "storage")
    @JsonIgnore
    private List<Part> parts;

    public Storage() {
    }

    public Storage(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
