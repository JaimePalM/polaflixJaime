package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Embeddable;

@Embeddable
public class SeasonViews {

    private List<Boolean> chapters;

    // Constructor
    public SeasonViews(int numChapters) {
        this.chapters = new ArrayList<Boolean>(numChapters);
    }

    // Auxiliar methods
    public void markChapterViewed(int chapter) {
        this.chapters.set(chapter, true);
    }

    // Getters and setters
    public List<Boolean> getChapters() {
        return this.chapters;
    }

    public void setChapters(List<Boolean> chapters) {
        this.chapters = chapters;
    }
}
