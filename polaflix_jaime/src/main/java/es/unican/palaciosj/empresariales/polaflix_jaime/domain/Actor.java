package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import jakarta.persistence.Embeddable;

/**
 * Actors class
 */
@Embeddable
public class Actor {
    
    // Atributes
    private String name;
    private String surname;

    // Constructor
    public Actor() { }
    public Actor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    // Auxiliar methods

    // Override methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Actor)) {
            return false;
        }
        Actor actor = (Actor) o;
        return this.name == actor.name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
