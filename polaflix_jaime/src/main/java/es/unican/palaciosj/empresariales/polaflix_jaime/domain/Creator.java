package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Creator class
 */
@Embeddable
public class Creator {
    
    // Atributes
    private String name;
    private String surname;

    // Constructor
    public Creator() { }
    public Creator(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    // Auxiliar methods

    // Override methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Creator)) {
            return false;
        }
        Creator creator = (Creator) o;
        return this.name == creator.name;
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
