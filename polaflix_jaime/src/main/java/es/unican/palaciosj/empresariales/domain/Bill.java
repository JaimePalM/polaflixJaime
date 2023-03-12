package es.unican.palaciosj.empresariales.domain;

import java.sql.Date;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

/**
 * Bill class
 */
@Entity
public class Bill {
    
    // Atributes
    private double totalAmount;
    private Date month;
    @ManyToMany
    private Set<ChapterView> monthViews = new TreeSet<ChapterView>();

    // Constructor
    public Bill(Date month) {
        this.month = month;
    }

    // Getters and Setters
    public double getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getMonth() {
        return this.month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Set<ChapterView> getMonthViews() {
        return this.monthViews;
    }

    public void setMonthViews(Set<ChapterView> monthViews) {
        this.monthViews = monthViews;
    }
}
