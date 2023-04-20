package es.unican.palaciosj.empresariales.polaflix_jaime.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.palaciosj.empresariales.polaflix_jaime.rest.JsonViews;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;

/**
 * Bill class
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Bill implements Comparable<Bill>{
    
    // Atributes
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonView(JsonViews.BillView.class)
    private double totalAmount;
    @JsonView(JsonViews.BillView.class)
    private Date monthBilled;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ElementCollection
    @JsonView(JsonViews.BillView.class)
    private Set<ChapterView> monthViews = new TreeSet<ChapterView>();

    // Constructor
    public Bill() { }
    public Bill(User user, Date month) {
        this.user = user;
        this.monthBilled = month;
        this.totalAmount = 0;
    }

    // Auxiliar methods
    public void addChapterView(Chapter chapter) {
        LocalDate date = LocalDate.now();
        Date today = Date.valueOf(date);
        ChapterView chapterView = new ChapterView(chapter, today, chapter.getSeason().getSerie().getCategory().getPrice());
        this.monthViews.add(chapterView);
        if (this instanceof OnDemandBill) {
            this.totalAmount += chapterView.getPrice();
        }
    }

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
