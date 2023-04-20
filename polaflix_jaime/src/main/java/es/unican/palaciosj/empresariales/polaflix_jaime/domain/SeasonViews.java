package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.palaciosj.empresariales.polaflix_jaime.rest.JsonViews;
import jakarta.persistence.Embeddable;

@Embeddable
public class SeasonViews {

    // Atributes
    @JsonView(JsonViews.SerieViewsView.class)
    private List<Boolean> chapters;

    // Constructor
    public SeasonViews() { }
    public SeasonViews(int numChapters) {
        this.chapters = new ArrayList<Boolean>(numChapters);
        for (int i = 0; i < numChapters; i++) {
            this.chapters.add(false);
        }
    }

    // Auxiliar methods
    public void markChapterViewed(int chapter) {
        // Try to mark chapter as viewed if is out of bounds, increase array size
        try {
            this.chapters.set(chapter, true);
        } catch (IndexOutOfBoundsException e) {
            ((ArrayList<Boolean>) this.chapters).ensureCapacity(chapter + 1);
            this.chapters.set(chapter, true);
        }
    }

    // Getters and setters
    public List<Boolean> getChapters() {
        return this.chapters;
    }

    public void setChapters(List<Boolean> chapters) {
        this.chapters = chapters;
    }
}
