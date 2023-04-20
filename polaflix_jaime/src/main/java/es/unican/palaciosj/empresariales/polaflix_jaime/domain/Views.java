package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.palaciosj.empresariales.polaflix_jaime.rest.JsonViews;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Views implements Comparable<Views> {
    
    // Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ElementCollection
    @JsonView(JsonViews.SerieViewsView.class)
    private List<SeasonViews> serieChapterViews;
    @OneToOne
    private Serie serie;

    // Constructor
    public Views() { }
    public Views(Serie serie) {
        this.serie = serie;
        // Initialize array with seasons as rows and chapters as columns
        this.serieChapterViews = new ArrayList<SeasonViews>();
        Iterator<Season> it = serie.getSeasons().iterator();
        int chapters = 0;
        while (it.hasNext()) {
            chapters = it.next().getChapters().size();
            SeasonViews seasonViews = new SeasonViews(chapters);
            this.serieChapterViews.add(seasonViews);
        }
    }

    // Auxiliar methods
    public void markChapterViewed(Chapter chapter) {
        int seasonIndex = chapter.getSeason().getNumber() - 1;
        int chapterIndex = chapter.getNumber() - 1;
        this.serieChapterViews.get(seasonIndex).markChapterViewed(chapterIndex);
    }

    // Override methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Views)) {
            return false;
        }
        Views views = (Views) o;
        return this.serie.equals(views.getSerie());
    }

    @Override
    public int hashCode() {
        return this.serie.hashCode();
    }

    @Override
    public int compareTo(Views o) {
        return this.serie.compareTo(o.getSerie());
    }

    // Getters and setters
    /* 
    public boolean[][] getSerieChapterViews() {
        return serieChapterViews;
    }

    public void setSerieChapterViews(boolean[][] serieChapterViews) {
        this.serieChapterViews = serieChapterViews;
    }
    */

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

}
