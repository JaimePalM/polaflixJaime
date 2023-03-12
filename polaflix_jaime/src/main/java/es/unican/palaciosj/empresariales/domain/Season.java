package es.unican.palaciosj.empresariales.domain;

import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.Embeddable;

/**
 * Season class
 */
@Embeddable
public class Season {

    // Atributes
    private int number;
    private Set<Chapter> chapters = new TreeSet<Chapter>();
    private Serie serie;

    // Constructor
    public Season(int number) {
        this.number = number;
    }

    // Auxiliar methods
    

    // Getters and Setters
    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<Chapter> getChapters() {
        return this.chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
    }

    public Serie getSerie() {
        return this.serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

}
