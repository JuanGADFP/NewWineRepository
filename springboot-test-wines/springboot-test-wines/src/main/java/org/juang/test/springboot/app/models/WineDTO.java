package org.juang.test.springboot.app.models;


import java.util.*;

public class WineDTO {

    private Long id;
    private String name;
    private String winery;
    private int año;
    private List<Owner> owners;

    // Constructors
    public WineDTO() {}

    public WineDTO(Long id, String name, String winery, int año, List<Owner> owners) {
        this.id = id;
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

    // toString method
    @Override
    public String toString() {
        return "WineDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", winery='" + winery + '\'' +
                ", año=" + año +
                ", owners=" + owners +
                '}';
    }
}