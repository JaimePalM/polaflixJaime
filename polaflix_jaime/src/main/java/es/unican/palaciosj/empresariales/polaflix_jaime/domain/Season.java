package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * Season class
 */
@Embeddable
public class Season implements Comparable<Season>{

    // Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int number;
    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private Set<Chapter> chapters = new TreeSet<Chapter>();
    private Serie serie;

    // Constructor
    public Season(int number, Serie serie) {
        this.number = number;
        this.serie = serie;
    }

    // Auxiliar methods
    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
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

        boolean seasonEquals = (number == season.number) && (serie.equals(season.serie));
        
        return seasonEquals;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        
        result = prime * result + number;
        
        if (serie != null) {
            result = prime * result + serie.hashCode();
        }

        return result;
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
