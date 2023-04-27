package org.juang.test.springboot.app.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Entity
@Table(name = "wines")
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String winery;
    private int año;
    private List<Owner> owners;

    // Constructor
    public Wine() {}

    public Wine(Long id,String name, String winery, int año) {
        this.id = id;
        this.name = name;
        this.winery = winery;
        this.año = año;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWinery() {
        return winery;
    }

    public void setWinery(String winery) {
        this.winery = winery;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }


    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public List<Owner> getOwners() {
        return owners;
    }
}
