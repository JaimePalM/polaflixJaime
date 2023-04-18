package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.palaciosj.empresariales.polaflix_jaime.rest.JsonViews;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/**
 * Season class
 */
@Entity
public class Season implements Comparable<Season>{

    // Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonView(JsonViews.SerieView.class)
    private int number;
    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonView(JsonViews.SerieView.class)
    private Set<Chapter> chapters = new TreeSet<Chapter>();
    @ManyToOne
    @JsonBackReference
    @JsonView(JsonViews.BillView.class)
    private Serie serie;

    // Constructor
    public Season() { }
    public Season(int number, Serie serie) {
        this.number = number;
        this.serie = serie;
    }

    // Auxiliar methods
    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
    }

    public Chapter getChapter(int number) {
        for (Chapter chapter : this.chapters) {
            if (chapter.getNumber() == number) {
                return chapter;
            }
        }
        return null;
    }
    
    // Override methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Season)) {
            return false;
        }
        Season season = (Season) o;

        return (this.serie.getId() == season.getSerie().getId()) && (this.number == season.getNumber());
    }

    @Override
    public int hashCode() {
        return serie.hashCode() * this.getNumber();
    }

    @Override
    public int compareTo(Season season) {
        int result = (int) (this.getSerie().getId() - season.getSerie().getId());
        if (result == 0) {
            result = this.getNumber() - season.getNumber();
        }
        return result;
    }

    // Getters and Setters
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
