package es.unican.palaciosj.empresariales.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.Entity;

/**
 * Serie class
 */
@Entity
public class Serie {
    
    // Atributes
    private String title;
    private String description;
    private Category category;
    private Set<Season> seasons = new TreeSet<Season>();
    private Set<Creator> creators = new HashSet<Creator>();
    private Set<Actors> actors = new HashSet<Actors>();

    // Constructor
    public Serie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Auxiliar methods


    // Getters and Setters
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Season> getSeasons() {
        return this.seasons;
    }

    public void setSeasons(Set<Season> seasons) {
        this.seasons = seasons;
    }

    public Set<Creator> getCreators() {
        return this.creators;
    }

    public void setCreators(Set<Creator> creators) {
        this.creators = creators;
    }

    public Set<Actors> getActors() {
        return this.actors;
    }

    public void setActors(Set<Actors> actors) {
        this.actors = actors;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
