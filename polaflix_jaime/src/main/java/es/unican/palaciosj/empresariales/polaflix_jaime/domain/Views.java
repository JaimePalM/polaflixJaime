package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.util.Iterator;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Embeddable
public class Views implements Comparable<Views> {
    
    // Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean[][] serieChapterViews;
    @OneToOne
    private Serie serie;

    // Constructor
    public Views(Serie serie) {
        this.serie = serie;
        // Inicialice array with seasons as rows and chapters as columns
        Iterator<Season> it = serie.getSeasons().iterator();
        int seasons = serie.getSeasons().size();
        int chapters = 0;
        while (it.hasNext()) {
            chapters = Math.max(chapters, it.next().getChapters().size());
        }
        this.serieChapterViews = new boolean[seasons][chapters];
    }

    // Auxiliar methods
    public void markChapterViewed(Chapter chapter) {
        this.serieChapterViews[chapter.getSeason().getNumber()][chapter.getNumber()] = true;
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
    public boolean[][] getSerieChapterViews() {
        return serieChapterViews;
    }

    public void setSerieChapterViews(boolean[][] serieChapterViews) {
        this.serieChapterViews = serieChapterViews;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

}
