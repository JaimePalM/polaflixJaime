package es.unican.palaciosj.empresariales.domain;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Serie {
    
    // Atributes
    private String title;
    private String description;
    private double price;
    private List<Season> seasons = new LinkedList<Season>();
    private Collection<Creator> creators = new LinkedList<Creator>();
    private Collection<Actors> actors = new LinkedList<Actors>();

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
    
    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Season> getSeasons() {
        return this.seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public Collection<Creator> getCreators() {
        return this.creators;
    }

    public void setCreators(Collection<Creator> creators) {
        this.creators = creators;
    }

    public Collection<Actors> getActors() {
        return this.actors;
    }

    public void setActors(Collection<Actors> actors) {
        this.actors = actors;
    }

  
       
}
