package es.unican.palaciosj.empresariales.domain;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

/**
 * Chapter view class
 */
@Entity
public class ChapterView {
    
    // Attributes
    @ManyToOne
    private Serie serie;
    private int chapterNum;
    private int seasonNum;
    private Date dateView;
    private double price;

    // Constructor
    public ChapterView(Serie serie, int chapterNum, int seasonNum, Date dateView, double price) {
        this.serie = serie;
        this.chapterNum = chapterNum;
        this.seasonNum = seasonNum;
        this.dateView = dateView;
        this.price = price;
    }

    // Auxiliar methods

    // Getters and Setters    
    public Serie getSerie() {
        return this.serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    } 
    
    public int getSeasonNum() {
        return this.seasonNum;
    }

    public void setSeasonNum(int seasonNum) {
        this.seasonNum = seasonNum;
    }
    
    public int getChapter() {
        return this.chapterNum;
    }

    public void setChapter(int chapterNum) {
        this.chapterNum = chapterNum;
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
