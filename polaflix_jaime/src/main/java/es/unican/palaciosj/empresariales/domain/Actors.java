package es.unican.palaciosj.empresariales.domain;

import jakarta.persistence.Embeddable;

/**
 * Actors class
 */
@Embeddable
public class Actors {
    
    // Atributes
    private String name;

    // Constructor
    public Actors(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
