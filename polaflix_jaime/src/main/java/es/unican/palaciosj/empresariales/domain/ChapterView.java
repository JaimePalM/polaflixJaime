package es.unican.palaciosj.empresariales.domain;

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


    // Override methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ChapterView)) {
            return false;
        }
        ChapterView chapterView = (ChapterView) o;

        boolean chapterViewEquals = (chapterNum == chapterView.chapterNum) && (seasonNum == chapterView.seasonNum) &&
                                    (serie.equals(chapterView.serie)) && (dateView.equals(chapterView.dateView));
        
        return chapterViewEquals;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        
        result = prime * result + chapterNum;
        result = prime * result + seasonNum;
        result = prime * result + dateView.hashCode();
        
        if (serie != null) {
            result = prime * result + serie.hashCode();
        }

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
