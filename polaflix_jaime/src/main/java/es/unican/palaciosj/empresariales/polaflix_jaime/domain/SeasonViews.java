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
}
