package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Creator class
 */
@Embeddable
public class Creator {
    
    // Atributes
    @Id
    @GeneratedValue
    private long id;
    private String name;

    // Constructor
    public Creator(String name) {
        this.name = name;
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
        return this.id == creator.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    // Getters and Setters
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
