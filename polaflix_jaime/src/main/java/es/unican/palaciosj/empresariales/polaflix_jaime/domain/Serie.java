package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.palaciosj.empresariales.polaflix_jaime.rest.JsonViews;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @JsonView({JsonViews.SerieView.class, JsonViews.SerieListView.class})
    private long id;
    @JsonView({JsonViews.UserView.class, JsonViews.SerieView.class, JsonViews.BillView.class, 
        JsonViews.ChapterView.class, JsonViews.SerieListView.class})
    private String title;
    @JsonView({JsonViews.SerieView.class})
    private String description;
    private char initial;
    @ManyToOne
    @JsonView({JsonViews.SerieView.class})
    private Category category;
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    @JsonView(JsonViews.SerieView.class)
    private Set<Season> seasons = new TreeSet<Season>();
    @ElementCollection
    @JsonView(JsonViews.SerieView.class)
    private Set<Creator> creators = new HashSet<Creator>();
    @ElementCollection
    @JsonView(JsonViews.SerieView.class)
    private Set<Actor> actors = new HashSet<Actor>();

    // Constructor
    public Serie() { }
    public Serie(String title, String description, Category category) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.initial = title.charAt(0);
    }

    // Auxiliar methods
    public void addSeason(Season season) {
        this.seasons.add(season);
    }

    public void addCreator(Creator creator) {
        this.creators.add(creator);
    }

    public void addActor(Actor actor) {
        this.actors.add(actor);
    }

    public Season getSeason(int number) {
        Season season = null;
        for (Season s : this.seasons) {
            if (s.getNumber() == number) {
                season = s;
            }
        }
        return season;
    }

    // Override methods
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Serie)) {
            return false;
        }
        Serie s = (Serie) o;
        return this.id == s.getId();
    }

    @Override
    public int hashCode() {
        return this.title.hashCode() + this.description.hashCode();
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

    public char getInitial() {
        return this.initial;
    }

    public void setInitial(char initial) {
        this.initial = initial;
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
