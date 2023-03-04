package es.unican.palaciosj.empresariales.domain;

import java.sql.Date;

public class ChapterView {
    
    // Attributes
    private Chapter chapter;
    private Date dateView;
    private double price;

    // Constructor
    public ChapterView(Date dateView, double price) {
        this.dateView = dateView;
        this.price = price;
    }

    // Auxiliar methods

    // Getters and Setters    
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
