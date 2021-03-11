package org.levelp.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Part {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false, length = 50)
    @NotBlank
    @Pattern(regexp = "[0-9]+(-[0-9]+)*")
    private String partNumber;

    @Column(nullable = false, length = 1000)
    @NotBlank
    private String title;

//    @PositiveOrZero
//    private int remaining;

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
