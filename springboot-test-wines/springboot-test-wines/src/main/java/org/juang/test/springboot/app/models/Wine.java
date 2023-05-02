package org.juang.test.springboot.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "wines")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String winery;
    private int año;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "wine_owners",
            joinColumns = @JoinColumn(name = "wine_id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id"))
    private List<Owner> owners;

    // Constructor
    public Wine() {}

    public Wine(String name, String winery, int año, List<Owner> owners) {
        this.name = name;
        this.winery = winery;
        this.año = año;
        this.owners = owners;
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

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }
}
