package org.levelp.model;

import javax.persistence.*;

@Entity
public class Part {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false, length = 50)
    private String partNumber;

    @Column(nullable = false, length = 1000)
    private String title;

    @ManyToOne
    @JoinColumn(name = "storage_fk")
    private Storage storage;

    public Part() {
    }

    public Part(String partNumber, String title) {
        this.partNumber = partNumber;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
