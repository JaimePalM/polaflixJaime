package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Chapter view class
 */
@Entity
public class ChapterView implements Comparable<ChapterView> {
    
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Chapter chapter;
    private Date dateView;
    private double price;

    // Constructor
    public ChapterView(Chapter chapter, Date dateView, double price) {
        this.chapter = chapter;
        this.dateView = dateView;
        this.price = price;
    }

    // Auxiliar methods


    // Override methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ChapterView)) {
            return false;
        }
        ChapterView chapterView = (ChapterView) o;

        boolean chapterViewEquals = (this.chapter == chapterView.chapter) && (dateView.equals(chapterView.dateView));
        
        return chapterViewEquals;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        Season season = chapter.getSeason();
        Serie serie = season.getSerie();
        
        result = prime * result + chapter.getNumber();
        result = prime * result + season.getNumber();
        result = prime * result + dateView.hashCode();
        result = prime * result + serie.hashCode();

        return result;
    }

    @Override
    public int compareTo(ChapterView o) {
        return this.dateView.compareTo(o.getDateView());
    }

    // Getters and Setters   
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
 
    public Chapter getChapter() {
        return this.chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Date getDateView() {
        return this.dateView;
    }

    public void setDateView(Date dateView) {
        this.dateView = dateView;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
