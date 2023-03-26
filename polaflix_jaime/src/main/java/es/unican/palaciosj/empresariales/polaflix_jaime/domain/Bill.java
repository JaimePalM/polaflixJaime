package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.sql.Date;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/**
 * Bill class
 */
@Entity
public class Bill implements Comparable<Bill>{
    
    // Atributes
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double totalAmount;
    private Date monthBilled;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ChapterView> monthViews = new TreeSet<ChapterView>();

    // Constructor
    public Bill(Date month) {
        this.monthBilled = month;
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
    public int hashCode() {
        return Long.hashCode(this.id);
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

    public Date getMonthBilled() {
        return this.monthBilled;
    }

    public void setMonthBilled(Date month) {
        this.monthBilled = month;
    }

    public Set<ChapterView> getMonthViews() {
        return this.monthViews;
    }

    public void setMonthViews(Set<ChapterView> monthViews) {
        this.monthViews = monthViews;
    }
}
