package es.unican.palaciosj.empresariales.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/**
 * Serie class
 */
@Entity
public class Serie implements Comparable<Serie> {
    
    // Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    @ManyToOne
    private Category category;
    @ElementCollection
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    private Set<Season> seasons = new TreeSet<Season>();
    @ElementCollection
    @ManyToMany
    private Set<Creator> creators = new HashSet<Creator>();
    @ElementCollection
    @ManyToMany
    private Set<Actor> actors = new HashSet<Actor>();

    // Constructor
    public Serie(String title, String description, Category category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }

    // Auxiliar methods
    public void addSeason(Season season) {
        this.seasons.add(season);
    }

    // Override methods
    @Override
    public boolean equals(Object o) {
        if (o instanceof Serie) {
            Serie s = (Serie) o;
            return this.id == s.getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public int compareTo(Serie o) {
        return Long.compare(this.id, o.getId());
    }

    // Getters and Setters
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Set<Actor> getActors() {
        return this.actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
