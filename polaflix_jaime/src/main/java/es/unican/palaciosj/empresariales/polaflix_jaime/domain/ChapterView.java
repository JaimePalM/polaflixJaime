package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.palaciosj.empresariales.polaflix_jaime.rest.JsonViews;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

/**
 * Chapter view class
 */
@Embeddable
public class ChapterView implements Comparable<ChapterView> {
    
    // Attributes
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JsonView(JsonViews.BillView.class)
    private Chapter chapter;
    @JsonView(JsonViews.BillView.class)
    private Date dateView;
    @JsonView(JsonViews.BillView.class)
    private double price;

    // Constructor
    public ChapterView() { }
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
        
        return this.id == chapterView.getId();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
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
