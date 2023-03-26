package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Chapter implements Comparable<Chapter> {

    // Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int number;
    private String title;
    private String description;
    private String url;
    @ManyToOne
    private Season season;

    // Constructor
    public Chapter() { }
    public Chapter(int number, String title, String description, String url, Season season) {
        this.number = number;
        this.title = title;
        this.description = description;
        this.url = url;
        this.season = season;
    }

    // Auxiliar methods


    // Override methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Chapter)) {
            return false;
        }
        Chapter chapter = (Chapter) o;

        return this.id == chapter.getId();
    }

    @Override
    public int hashCode() {
        int result = 17;
        
        result = 31 * result + number;
        
        if (season != null) {
            result = 31 * result + season.hashCode();
        }
        if (season != null && season.getSerie() != null) {
            result = 31 * result + season.getSerie().hashCode();
        }

        return result;
    }

    
    @Override
    public int compareTo(Chapter c) {
        if (this.season.getNumber() == c.getSeason().getNumber()) {
            return this.number - c.getNumber();
        } else {
            return this.season.getNumber() - c.getSeason().getNumber();
        }
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Season getSeason() {
        return this.season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

}
