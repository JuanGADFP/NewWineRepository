package org.juang.test.springboot.app.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "wines")
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(message = "El id no debe ser enviado")
    private Long id;
    @NotBlank(message = "El nombre del vino tiene que ser enviado")
    private String name;
    @NotBlank(message = "El winery del vino tiene que ser enviado")
    private String winery;
    @NotNull(message = "El año no puede ser null")
    private int año;

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
}
