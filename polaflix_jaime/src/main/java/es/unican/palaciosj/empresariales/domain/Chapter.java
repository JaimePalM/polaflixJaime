package es.unican.palaciosj.empresariales.domain;

import jakarta.persistence.Entity;

@Entity
public class Chapter {

    // Atributes
    private int number;
    private String title;
    private String description;
    private String url;
    private Season season;

    // Constructor
    public Chapter(int number, String title, String description, String url) {
        this.number = number;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    // Auxiliar methods
    public void play() {
        System.out.println("Playing chapter " + this.number + "...");
    }

    // Override methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Chapter)) {
            return false;
        }
        Chapter chapter = (Chapter) o;

        boolean chapterEquals = (number == chapter.number) && (season.equals(chapter.season) && (season.getSerie().equals(chapter.season.getSerie())));
        
        return chapterEquals;
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

    // Getters and Setters
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
