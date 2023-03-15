package es.unican.palaciosj.empresariales.domain;

import java.sql.Date;
import java.util.Set;
import java.util.TreeSet;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/**
 * Bill class
 */
@Entity
public class Bill implements Comparable<Bill>{
    
    // Atributes
    @Id 
    @GeneratedValue
    private long id;
    private double totalAmount;
    private Date month;
    @ManyToMany
    private Set<ChapterView> monthViews = new TreeSet<ChapterView>();

    // Constructor
    public Bill(Date month) {
        this.month = month;
    }

    // Auxiliar methods

    // Override methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Bill)) {
            return false;
        }
        Bill bill = (Bill) o;
        return this.id == bill.id;
    }

    @Override
    public int compareTo(Bill bill) {
        return Long.compare(this.id, bill.getId());
    }

    // Getters and Setters
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
